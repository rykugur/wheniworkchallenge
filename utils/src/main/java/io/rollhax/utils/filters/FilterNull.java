package io.rollhax.utils.filters;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class FilterNull<T> implements Predicate<T> {

    private FilterNull() {
        // use of(Class<Type> clazz)
    }

    public static <Type> FilterNull<Type> of(Class<Type> clazz) {
        return new FilterNull<>();
    }

    @Override
    public boolean test(T t) throws Exception {
        return t != null;
    }
}
