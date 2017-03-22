package com.seeyon.classes;

import sun.misc.IOUtils;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangyu on 2017/2/16.
 */
public class ClassUtil {

    public static void main(String[] args) throws Exception {
        String name = "com.seeyon.classes.MyClass";
        File file = new File("/Users/yangyu/Downloads/myclass");
        InputStream in = new FileInputStream(file);
        byte[] bytes = IOUtils.readFully(in, -1, false);
        String content = new String(bytes);
        in.close();

        Class cls = compile(name,content);
        Object o = cls.newInstance();
        System.out.println(o);
    }

    public final static Class compile(String name,String content) throws URISyntaxException {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null,null,null);
        StringJavaObject javaObject = new StringJavaObject(name,content);

        List<JavaFileObject> fileObjects = Arrays.asList(javaObject);

        String outDir = Thread.currentThread().getContextClassLoader().getResource("").getPath()+name.replace(".","/")+".class";

        List<String> options = new ArrayList<String>();
        options.add("-encoding");
        options.add("UTF-8");
//        options.add("-classpath");
//        options.add(outDir);

        JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, options, null, fileObjects);

        boolean result = task.call();
        if (result == true) {
            System.out.println("Compile it successfully.");
            try {
                /**
                 * 这里直接Class.forName是不能获取到Class的，应为编译后的二级制数据还没有被加载
                 */
                return Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static class StringJavaObject extends SimpleJavaFileObject{

        private String content;

        public StringJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }
}
