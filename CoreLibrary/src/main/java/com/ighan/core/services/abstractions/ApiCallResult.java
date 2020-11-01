package com.ighan.core.services.abstractions;

public interface ApiCallResult<T> {

    void onSuccess(T result);

    void onError(Throwable error);
}
