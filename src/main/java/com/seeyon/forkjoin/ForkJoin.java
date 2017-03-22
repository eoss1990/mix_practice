package com.seeyon.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by yangyu on 2017/3/1.
 */
public class ForkJoin extends RecursiveTask<Integer>{

    private static final int THRESHOLD = 100;

    private int start;

    private int end;

    public ForkJoin(int start,int end){
        super();
        this.start=start;
        this.end=end;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getId());
        int sum=0;

        if (end-start<20){
            for (int i = start; i <= end; i++) {
                sum = sum + i;
            }
        }else{
            int middle = (start+end)/2;
            ForkJoin leftFork = new ForkJoin(start,middle);
            ForkJoin rightFork = new ForkJoin(middle+1,end);
            leftFork.fork();
            rightFork.fork();
            sum = leftFork.join()+rightFork.join();
        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoin forkJoinTask = new ForkJoin(0,1000*1000);
        forkJoinPool.submit(forkJoinTask);
        try {
            int sum = forkJoinTask.get();
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int result=0;
        for (int i=0;i<=1000*1000;i++){
            result+=i;
        }
        System.out.println(result);
    }
}
