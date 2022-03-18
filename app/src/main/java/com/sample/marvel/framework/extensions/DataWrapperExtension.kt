package com.sample.marvel.framework.extensions

import com.sample.marvel.ui.DataWrapper
import io.reactivex.subjects.Subject

fun <T> Subject<DataWrapper<T>>.toLoading() {
    onNext(DataWrapper.loading(null))
}

fun <T> Subject<DataWrapper<T>>.toSuccess(result: T) {
    onNext(DataWrapper.success(result))
}

fun <T> Subject<DataWrapper<T>>.toError(message: String) {
    onNext(DataWrapper.error(message, null))
}