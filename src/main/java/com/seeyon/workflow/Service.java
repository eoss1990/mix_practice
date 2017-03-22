package com.seeyon.workflow;

/**
 * Created by yangyu on 16/10/31.
 */
public abstract class Service extends AbstractAdapter implements Cloneable,Runnable{
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public void run() {
        if (rout(this.getCondition()))
            execute(null);
    }
}
