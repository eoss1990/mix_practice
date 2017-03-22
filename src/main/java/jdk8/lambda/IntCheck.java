package jdk8.lambda;

import java.util.function.Predicate;

/**
 * Created by yangyu on 2017/3/21.
 */
public interface IntCheck {

    boolean check(Predicate<Integer> predicate);

//    boolean check(IntPred intPred);
}
