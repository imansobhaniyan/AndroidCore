package com.ighan.core.services.abstractions;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ighan.core.storage.ApiSharedPreferences;
import com.ighan.core.utilities.date.DateFormatter;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseService<TApi> {

    protected int API_TIME_OUT = 15000;

    private static Retrofit retrofit;

    private TApi api;

    protected BaseService(Class<TApi> service) {
        setApi(service);
    }

    protected String getToken(Context context) {
        return ApiSharedPreferences.getInstance(context).getToken();
    }

    protected <T> void doObserveAndSubscribe(Observable<T> observable, final ApiCallResult<T> callResult) {
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(T t) {
                        callResult.onSuccess(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callResult.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    protected TApi getApi() {
        return api;
    }

    protected abstract String getBaseUrl();

    private void setApi(Class<TApi> service) {
        if (retrofit == null) {

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
                    .readTimeout(API_TIME_OUT, TimeUnit.MILLISECONDS)
                    .build();


            retrofit = new Retrofit.Builder()
                    .baseUrl(getBaseUrl())
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(makeGson()))
                    .build();

        }
        api = retrofit.create(service);
    }

    private Gson makeGson() {
        return new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    protected String formatDate(Date date) {
        return DateFormatter.getString(date, DateFormatter.DateTimePattern.DATE_TIME_SECONDS);
    }
}
