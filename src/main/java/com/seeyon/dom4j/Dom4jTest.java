package com.seeyon.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by yangyu on 16/10/29.
 */
public class Dom4jTest {
    public static void main(String[] args) {

        String path = new Object().getClass().getResource("/").getPath()+"data.xml";
        File file = new File(path);
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(file);
            /**
             * 使用xpath，获取节点非常灵活
             */
            List list = document.selectNodes("/root/table//name");
            for (Object element:list){
                Element ele = (Element)element;
                /**
                 * 改变Element名称
                 */
                ele.setName("name2");
                System.out.println(ele.getText());
                ele.setText("linling");
            }

            System.out.println(document.selectSingleNode("//name2").getText());

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试--i，与i--的区别
     * --i：运算优先于赋值
     * i--：赋值优先于运算
     */
    @Test
    public void test(){
        String[] strings = {"yangyu","linling","yangming"};
        int i = 2;
        System.out.println(strings[--i]);

        int j = 5;
        int k = --j;
        System.out.println(k+","+j);
    }

    /**
     * 此方法证明Clone方法只是浅克隆，要实现深度克隆的话可是使用序列化与反序列化。
     * @throws CloneNotSupportedException
     */
    @Test
    public void testClone() throws CloneNotSupportedException {

        Node node1 = new Node("yangyu");
        JavaBean javaBean = new JavaBean(node1);
        String abc = javaBean.doSome("nihao");

        JavaBean javaBean1 = (JavaBean) javaBean.clone();
        System.out.println(javaBean.node.hashCode());
        System.out.println(javaBean1.node.hashCode());
    }

    /**
     * 使用匿名内部类
     */
    @Test
    public void testFly(){
        useFly(new Fly() {
            @Override
            public void fly() {
                System.out.println("fly,fly,fly");
            }
        });
    }



    public void useFly(Fly fly){
        fly.fly();
    }

    private static class JavaBean implements Cloneable{

        public Node node;

        public JavaBean(Node node){
            this.node=node;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        public <T> T doSome(T node){
            return node;
        }
    }

    private static class Node{

        String name;

        Node(String name){
            this.name = name;
        }
    }

    private interface Fly{
        public void fly();
    }

    /**
     * enum枚举用法
     */
    public enum Color{

        RED(0),YELLOW(1);

        /**
         * 必须定义属性，上面的枚举才能有属性
         */
        public int num;

        /**
         * RED(0)相当于调用此构造方法，实例化了一个名称为RED的Color枚举对象，拥有一个属性名称为num，值为0
         * @param num
         */
        private Color(int num){
            this.num =num;
        }
    }
}
