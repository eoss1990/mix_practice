package com.seeyon.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * Created by yangyu on 16/12/29.
 * 强引用：不会被回收
 * 软引用：SoftReference，当系统内存不足时，JVM才会回收
 * 弱引用：WeakReference，只要gc就会被回收
 * 虚引用：PhantomReference，
 */
public class FourReference {
    /**
     * 弱引用
     * @param args
     */
    public static void main(String[] args) {

        MyObject myObject = new MyObject();

        WeakReference<MyObject> weakReference = new WeakReference(myObject);
        myObject=null;
        System.out.println("before gc:weak:"+weakReference.get());
        System.gc();
        System.out.println("after gc:weak:"+weakReference.get());
    }

    public static class MyObject{
        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("MyObject is finalize");
        }
    }

    /**
     * 虚引用
     * @throws InterruptedException
     */
    @Test
    public void TestPhantomReference() throws InterruptedException {
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject,referenceQueue);
        phantomReference.get();
        myObject = null;
        System.gc();
        /**
         * 会一直阻塞，直到JVM真正回收该对象
         */
        referenceQueue.remove();
        System.out.println("wancheng");
    }
}
