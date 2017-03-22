package com.seeyon.workflow;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyu on 16/10/30.
 */
public abstract class AbstractAdapter implements Adapter {

    /**
     * 判断条件，判断该适配器是否执行
     */
    private String condition;

    /**
     * 该适配的下一个适配器
     */
    private List<AbstractAdapter> next = new ArrayList<>();

    /**
     * 该适配器的上一个适配器
     * 现在暂时没用，备用以便解决流程循环的问题
     */
    private List<AbstractAdapter> pre;

    /**
     * 适配器必须重写此方法，完成业务处理
     * @param document
     * @return
     */
    @Override
    public abstract Document execute(Document document);

    /**
     * 递归方法，递归执行所有next适配器
     * @param document
     * @return
     */
    public final Document workflow(Document document){

        if (!rout(this.condition))
            return document;

        document = execute(document);
        if (next==null||next.size()==0)
            return document;

        Document doc = null;
        for (AbstractAdapter nextAdapter : next ){
            if (next.size()==1)
                doc = nextAdapter.workflow(document);
            else
                doc = SeUtil.merge(doc,nextAdapter.workflow((Document) document.clone()));
        }

        return doc;
    }

    /**
     * 适配器必须重写此方法，完成条件判断
     * @param condition
     * @return
     */
    @Override
    public abstract boolean rout(String condition);

    public final String getCondition() {
        return condition;
    }

    public final void setCondition(String condition) {
        this.condition = condition;
    }

    public final List<AbstractAdapter> getNext() {
        return next;
    }

    public final void setNext(List<AbstractAdapter> next) {
        this.next = next;
    }

    public final void setNext(AbstractAdapter adapter) {
        this.next.add(adapter);
    }

    public final List<AbstractAdapter> getPre() {
        return pre;
    }

    public final void setPre(List<AbstractAdapter> pre) {
        this.pre = pre;
    }
}
