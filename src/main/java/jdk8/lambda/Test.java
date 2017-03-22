package jdk8.lambda;

import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;

/**
 * Created by yangyu on 2017/3/21.
 */
public class Test {

    public static void main(String[] args) {

        //lambda
        Runnable myRun = () -> {
            System.out.println(111);
            System.out.println(222);
        };
        new Thread(myRun).start();

        //lambda实现BooleanSupplier
        BooleanSupplier booleanSupplier = () -> true;
        System.out.println(booleanSupplier.getAsBoolean());

        //lambda实现BinaryOperator
        BinaryOperator<Integer> binaryOperator = (x, y) -> {
            x = 2 * x;
            return x + y;
        };
        System.out.println(binaryOperator.apply(100, 200));

        //lambda实现ThreadLocal
        ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "yangyu");
        System.out.println(threadLocal.get());

        //lambda实现方法并传递方法
        IntCheck intCheck = predicate -> predicate.test(1);
        Predicate<Integer> predicate = x -> x > 5;
        System.out.println(intCheck.check(predicate));

    }
}
