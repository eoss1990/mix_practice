package com.seeyon.workflow;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangyu on 16/10/31.
 */
public class ServiceFactory {

    private static final String HOME = new Object().getClass().getResource("/").getPath() + "workflow/";
    private static final String FILE_NAME = "workflow.xml";
    private static final String ADAPTERS = "adapters";
    private static final String ROUT = "rout";
    /**
     * 服务缓存
     */
    private ConcurrentHashMap services = new ConcurrentHashMap();
    /**
     * 服务定义缓存
     */
    private ConcurrentHashMap<String, HashMap> servicesDefinition = new ConcurrentHashMap<>();

    /**
     * 初始化方法，将workflow.xml加载到内存当中
     */
    @Test
    public void init() {
        Document document;
        File file = new File(HOME + FILE_NAME);
        SAXReader saxReader = new SAXReader();
        try {
            document = saxReader.read(file);
            parse(document);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析services区
     * @param document workflow.xml
     */
    public void parse(Document document) {
        List<Element> services = document.getRootElement().elements();
        for (Element service : services) {
            String serviceId = service.attribute("id").getValue();
            if (StringUtils.isEmpty(serviceId))
                new Exception("serviceId为空，请检查配置").printStackTrace();
            servicesDefinition.put(serviceId, parseService(service));
        }
    }

    /**
     * 解析每个service
     * @param service
     * @return 完整服务定义
     */
    public HashMap parseService(Element service) {
        HashMap serviceDefinition = new HashMap();
        List<Element> nodes = service.elements();
        for (Element node : nodes) {
            if (ROUT.equals(node.getName()))
                serviceDefinition.put("rout", parseRout(node));
            if (ADAPTERS.equals(node.getName()))
                serviceDefinition.put("adapters", parseAdapters(node));
        }
        return serviceDefinition;
    }

    /**
     * 解析rout区
     * @param rout
     * @return 服务流程
     */
    public HashMap parseRout(Element rout) {
        HashMap routDefinition = new HashMap();
        List<Element> nodes = rout.elements();

        if (nodes.size() == 0)
            return null;

        for (Element node : nodes)
            routDefinition.put(node.attribute("id").getValue(), parseRout(node));
        return routDefinition;
    }

    /**
     * 解析rout区的另一种实现
     * @param rout
     * @return 服务流程
     */
    public HashMap parseRoutOther(Element rout) {
        HashMap routDefinition = new HashMap();
        List<Element> nodes = rout.elements();

        for (Element node : nodes) {
            if (node.elements().size() == 0) {
                routDefinition.put(node.attribute("id").getValue(), null);
                return routDefinition;
            }
            routDefinition.put(node.attribute("id").getValue(), parseRoutOther(node));
        }

        return routDefinition;
    }

    /**
     * 解析Adapters区
     * @param adapters
     * @return 服务节点定义
     */
    public HashMap parseAdapters(Element adapters) {
        HashMap adaptersDefinition = new HashMap();
        List<Element> adapterList = adapters.elements();
        if (adapterList.size() == 0)
            return adaptersDefinition;

        for (Element adapter : adapterList) {
            HashMap adapterDefinition = new HashMap();
            List<Element> propertyList = adapter.elements();
            for (Element property : propertyList)
                adapterDefinition.put(property.attribute("name").getValue(), property.attribute("value").getValue());
            adapterDefinition.put("class", adapter.attribute("class").getValue());
            adaptersDefinition.put(adapter.attribute("id").getValue(), adapterDefinition);
        }
        return adaptersDefinition;
    }

    /**
     * 从内存中获取service对象，返回的是service对象副本
     * @param serviceId 服务ID
     * @return service对象
     */
    public Service getService(String serviceId) {
        Service service = (Service) services.get(serviceId);
        try {
            if (service != null)
                return (Service) service.clone();

            service = createService(serviceId);
            services.put(serviceId, service);
            return (Service) service.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建service
     * @param serviceId 服务ID
     * @return service对象
     */
    public Service createService(String serviceId) {
        HashMap serviceDefinition = servicesDefinition.get(serviceId);
        if (serviceDefinition == null)
            return null;

        HashMap<String, Object> routDefinition = (HashMap) serviceDefinition.get(ROUT);
        HashMap adaptersDefinition = (HashMap) serviceDefinition.get(ADAPTERS);

        return serviceHandler(null, routDefinition, adaptersDefinition);

    }

    /**
     * 构建service链表关系
     * @param service 服务对象
     * @param routDefinition 服务流程
     * @param adaptersDefinition 服务定义
     * @return service对象
     */
    public Service serviceHandler(Service service, HashMap<String, Object> routDefinition, HashMap adaptersDefinition) {

        Service ser = null;

        for (Map.Entry entry : routDefinition.entrySet()) {
            if (entry.getValue() instanceof HashMap) {
                ser = createAdapter((HashMap) adaptersDefinition.get(entry.getKey()));
                if (service == null)
                    service = ser;
                else
                    service.setNext(ser);
                serviceHandler(ser, (HashMap<String, Object>) entry.getValue(), adaptersDefinition);
            } else {
                service.setNext(createAdapter((HashMap) adaptersDefinition.get(entry.getKey())));
            }
        }
        return service;
    }

    /**
     * 创建服务中的节点
     * @param adapterDefinition 节点定义
     * @return 服务中的节点
     */
    public Service createAdapter(HashMap adapterDefinition) {

        Service service = null;
        try {
            Class clazz = Class.forName(adapterDefinition.get("class").toString());
            service = (Service) clazz.newInstance();
            Field field;

            for (Map.Entry entry : ((HashMap<String, Object>) adapterDefinition).entrySet()) {
                if ("class".equals(entry.getKey().toString()))
                    continue;
                if ("condition".equals(entry.getKey().toString())) {
                    Method method = clazz.getMethod(SeUtil.getSetMethod("condition"), String.class);
                    method.invoke(service, entry.getValue());
                    continue;
                }
                field = clazz.getDeclaredField(entry.getKey().toString());
                field.setAccessible(true);
                field.set(service, entry.getValue());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return service;
    }

}
