package com.dindin.hotrovndemo.utils;

import com.google.gson.reflect.TypeToken;

public final class GenericBody<T> {
    final T body;
    final TypeToken<T> typeToken;
    public GenericBody(final T body, final TypeToken<T> typeToken) {
        this.body = body;
        this.typeToken = typeToken;
    }
}