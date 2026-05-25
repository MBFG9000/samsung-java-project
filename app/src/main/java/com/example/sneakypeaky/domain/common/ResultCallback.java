package com.sneakypeaky.domain.common;

public interface ResultCallback<T> {
    void onSuccess(T data);

    void onError(String message);
}
