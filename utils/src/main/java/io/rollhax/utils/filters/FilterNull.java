package io.rollhax.utils.filters;

import io.reactivex.functions.Function;

public class FilterNull<T> implements Function<T, Boolean> {

    private FilterNull() {
        // use of(Class<Type> clazz)
    }

    public static <Type> FilterNull<Type> of(Class<Type> clazz) {
        return new FilterNull<>();
    }

    @Override
    public Boolean apply(T t) throws Exception {
        return t != null;
    }
}
