package jdk8.stream;

import jdk8.bean.Cat;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yangyu on 2017/3/21.
 */
public class TestStream {

    /**
     * array->stream->array
     *
     * @param args
     */
    public static void main(String[] args) {
        List<String> collected = Stream.of("a", "b", "c").collect(Collectors.toList());

        Assert.assertEquals(Arrays.asList("a", "b", "c"), collected);
    }

    /**
     * stream map
     */
    @Test
    public void streamMap() {
        List<String> collected = Stream.of("a", "b", "c").map(string -> string.toUpperCase())
                .collect(Collectors.toList());

        Assert.assertEquals(Arrays.asList("A", "B", "C"), collected);
    }

    /**
     * stream filter
     */
    @Test
    public void streamFilter() {

        List<String> colloected = Stream.of("yy", "yangyu", "linglin", "yangming").
                filter(string -> {
                    if (string.startsWith("y")) {
                        return true;
                    }
                    return false;
                }).collect(Collectors.toList());

        //stream遍历
        Stream.of(colloected).forEach(string -> System.out.println(string));
    }

    /**
     * stream flatMap
     */
    @Test
    public void flatMap() {
        List<String> collected = Stream.of(Arrays.asList("1", "2"), Arrays.asList("3", "4")).
                flatMap(list -> list.stream()).collect(Collectors.toList());

        Stream.of(collected).forEach(strings -> System.out.println(strings));
    }

    /**
     * stream Max and Min
     */
    @Test
    public void streamMaxAndMin() {
        Cat cat1 = new Cat();
        cat1.setWeight(10);
        Cat cat2 = new Cat();
        cat2.setWeight(20);
        Cat cat3 = new Cat();
        cat3.setWeight(30);

        Cat maxCat = Stream.of(cat1, cat2, cat3).max(Comparator.comparing(Cat::getWeight)).get();
        System.out.println(maxCat.getWeight());

        Cat minCat = Stream.of(cat1, cat2, cat3).min(Comparator.comparing(Cat::getWeight)).get();
        System.out.println(minCat.getWeight());
    }

    /**
     * stream Reduce 求和
     */
    @Test
    public void streamReduce() {
        int sum = Stream.of(1, 2, 3, 4, 5, 6).reduce(0, (acc, element) -> acc + element);
        System.out.println(sum);
    }


    /**
     * catList factory
     *
     * @return
     */
    public static List<Cat> catsFactory() {
        Cat cat1 = new Cat("cat1", 10);
        Cat cat2 = new Cat("cat2", 20);
        Cat cat3 = new Cat("cat3", 30);
        Cat cat4 = new Cat("cat4", 40);
        Cat cat5 = new Cat("cat5", 50);
        Cat cat6 = new Cat("cat6", 60);
        Cat cat7 = new Cat("cat7", 70);
        Cat cat8 = new Cat("cat8", 80);
        Cat cat9 = new Cat("cat9", 90);
        List<Cat> cats = Arrays.asList(new Cat[]{cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9});
        return cats;
    }

    /**
     * stream chain
     * 获取Cat List中weight>=50的Cat的名称
     * 1.获取stream
     * 2.通过filter获取weight>=50的cat
     * 3.通过map获取名称
     * 4.通过foreach遍历打印
     */
    @Test
    public void streamChain() {
        catsFactory().stream().filter(cat -> {
            if (cat.getWeight() >= 50) return true;
            return false;
        }).map(Cat::getName).forEach(name -> System.out.println(name));
    }

    /**
     * 通过reduce模拟Map的功能
     * 1.通过reduce BiFunction将所有cat聚合在一个catList里面，并且将所有weight设置为800
     * 2.通过reduce BinaryOperator同样是将所有cat聚合在一个catList里面
     * 3.最后获取stream进行遍历输出
     */
    @Test
    public void streamReduceForMap() {
        List<Cat> cats = new ArrayList<>();
        catsFactory().stream().reduce(cats, (catList, cat) -> {
            cat.setWeight(800);
            catList.add(cat);
            return catList;
        }, (left, right) -> {
            left.addAll(right);
            return left;
        }).stream().forEach(cat -> System.out.println(String.format("%s:%d", cat.getName(), cat.getWeight())));
    }

    /**
     * 通过Optional.isPresent()判断是否为null
     * 或者可以通过orElse，orElseGet获取其它值
     */
    @Test
    public void streamReduceForOptional() {
        Optional<Cat> optional = catsFactory().stream().reduce((cat1, cat2) -> {
            if (cat1.getWeight() < cat2.getWeight()) return cat1;
            return cat2;
        });
        if (optional.isPresent())
            System.out.println(optional.get().getName());
    }

    /**
     * 并行stream
     */
    @Test
    public void parallelStream(){
        AtomicInteger i = new AtomicInteger(0);
        IntStream.range(0,10000).parallel().forEach(j->i.getAndIncrement());
        System.out.println(i.get());
    }

}
