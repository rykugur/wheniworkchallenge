package io.rollhax.nextripservice.service;

import com.google.gson.Gson;

import java.util.List;
import java.util.concurrent.TimeUnit;

import api.NextripApi;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rollhax.nextripdomain.BuildConfig;
import io.rollhax.nextripdomain.IDeparture;
import io.rollhax.nextripdomain.IRoute;
import io.rollhax.nextripdomain.IStop;
import io.rollhax.utils.filters.FilterNull;
import io.rollhax.utils.transformers.FlattenCollection;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A retrofit service to consume the NexTrip API.
 */
public class NextripRetroService implements INextripService {

    private static final long DEFAULT_CONNECT_TIMEOUT_SECONDS = 30;
    private static final long DEFAULT_READ_TIMEOUT_SECONDS = 30;
    private static final long DEFAULT_WRITE_TIMEOUT_SECONDS = 30;
    private static final String BASE_URL = "http://svc.metrotransit.org/NexTrip/";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    private Gson mGson;
    private NextripApi mNextripApi;

    public NextripRetroService(Gson gson) {
        mGson = gson;

        init(DEFAULT_CONNECT_TIMEOUT_SECONDS, DEFAULT_READ_TIMEOUT_SECONDS, DEFAULT_WRITE_TIMEOUT_SECONDS);
    }

    public NextripRetroService(Gson gson, long connectTimeout, long readTimeout, long writeTimeout) {
        mGson = gson;

        init(connectTimeout, readTimeout, writeTimeout);
    }

    private void init(long connectTimeout, long readTimeout, long writeTimeout) {

        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient client = okhttpBuilder.addInterceptor(interceptor)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .build();
        mNextripApi = retrofit.create(NextripApi.class);
    }

    //reigon INextripService
    @Override
    public Observable<List<IRoute>> getRoutes() {
        return mNextripApi.getRoutes()
                .compose(FlattenCollection.of(IRoute.class))
                // get rid of junk data
                .filter(FilterNull.of(IRoute.class))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<IStop>> getStops(String route) {
        return mNextripApi.getStops(route)
                .compose(FlattenCollection.of(IStop.class))
                // get rid of junk data
                .filter(FilterNull.of(IStop.class))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<IStop>> getStops(String route, String direction) {
        return mNextripApi.getStops(route, direction)
                .compose(FlattenCollection.of(IStop.class))
                // get rid of junk data
                .filter(FilterNull.of(IStop.class))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<IDeparture>> getDepartures(int stopId) {
        return mNextripApi.getDepartures(stopId)
            .compose(FlattenCollection.of(IDeparture.class))
                    // get rid of junk data
                    .filter(FilterNull.of(IDeparture.class))
                    .toList()
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
    }
    //endregion
}
