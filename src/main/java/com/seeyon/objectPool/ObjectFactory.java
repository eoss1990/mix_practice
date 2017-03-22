package com.seeyon.objectPool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by yangyu on 16/12/26.
 */

/**
 * 对象工厂，用于创建对象
 */
public class ObjectFactory extends BasePooledObjectFactory<ObjectFactory.Computer> {


    /**
     * 创建对象，返回你需要的对象实例就可以啦
     * @return
     * @throws Exception
     */
    @Override
    public Computer create() throws Exception {
        return new Computer("mac");
    }

    /**
     * 包装对象，将对象包装为PooledObject
     * @param obj
     * @return
     */
    @Override
    public PooledObject<Computer> wrap(Computer obj) {
        return new DefaultPooledObject<>(obj);
    }

    public class Computer{

        String name;

        public Computer(String name){
            this.name = name;
        }
    }
}
