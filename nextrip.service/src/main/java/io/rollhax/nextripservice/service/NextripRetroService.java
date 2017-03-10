package io.rollhax.nextripservice.service;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import api.NextripApi;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rollhax.nextripdomain.BuildConfig;
import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.nextripdomain.models.Route;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.models.TextValuePair;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.utils.filters.FilterNull;
import io.rollhax.utils.transformers.FlattenCollection;
import mappers.DirectionTypeMapper;
import mappers.StopMapper;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
    //reigon INextripService
    @Override
    public Observable<List<Route>> getRoutes() {
        return mNextripApi.getRoutes()
                .compose(FlattenCollection.of(Route.class))
                // get rid of junk data
                .filter(FilterNull.of(Route.class))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<DirectionType>> getDirections(String route) {
        return mNextripApi.getDirections(route)
                .compose(FlattenCollection.of(TextValuePair.class))
                // get rid of junk data
                .filter(FilterNull.of(TextValuePair.class))
                // map from TextValuePair to DirectionType
                .map(new DirectionTypeMapper())
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Stop>> getStops(String route, int directionTypeId) {
        return getStops(route, String.valueOf(directionTypeId));
    }

    @Override
    public Observable<List<Stop>> getStops(String route, String direction) {
        return mNextripApi.getStops(route, direction)
                .compose(FlattenCollection.of(TextValuePair.class))
                // get rid of junk data
                .filter(FilterNull.of(TextValuePair.class))
                // map from TextValuePair to Stop
                .map(new StopMapper())
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Departure>> getDepartures(String route, int directionTypeId, String stopId) {
        return getDepartures(route, String.valueOf(directionTypeId), stopId);
    }

    @Override
    public Observable<List<Departure>> getDepartures(String route, String direction, String stopId) {
        return mNextripApi.getDepartures(route, direction, stopId)
                .compose(FlattenCollection.of(Departure.class))
                // get rid of junk data
                .filter(FilterNull.of(Departure.class))
                .toList()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    //endregion

    //region Private helpers
    private void init(long connectTimeout, long readTimeout, long writeTimeout) {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        if (DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient client = okhttpBuilder
                .addInterceptor(interceptor)
                .addInterceptor(mHeaderIntercptor)
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
    //endregion

    private static final Interceptor mHeaderIntercptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            Request.Builder builder = original.newBuilder();
            builder.header("Content-Type", "application/json");
            builder.header("Accept", "application/json");
            builder.method(original.method(), original.body());
            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }
    };
}
