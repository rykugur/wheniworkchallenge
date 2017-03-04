package io.rollhax.utils.transformers;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;

public class FlattenCollection<T> implements ObservableTransformer<List<T>, T> {

    private FlattenCollection() {
        // use of(Class<Type> clazz)
    }

    public static <Type> FlattenCollection<Type> of(Class<Type> clazz) {
        return new FlattenCollection<>();
    }

    @Override
    public ObservableSource<T> apply(Observable<List<T>> upstream) {
        return upstream.flatMap(new Function<List<T>, Observable<T>>() {
            @Override
            public Observable<T> apply(List<T> list) throws Exception {
                return Observable.fromIterable(list);
            }
        });
    }
}
