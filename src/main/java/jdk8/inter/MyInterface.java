package jdk8.inter;

/**
 * Created by yangyu on 2017/3/22.
 */
public interface MyInterface {

    /**
     * 静态方法不继承
     * @param name
     * @return
     */
    public static String say(String name) {
        return "hello world " + name;
    }

    /**
     * default方法要继承
     * @param words
     * @return
     */
    default public String speak(String words) {
        return "this is words:" + words;
    }
}
