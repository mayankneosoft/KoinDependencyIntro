package com.koin.dependencyIntro.data.utils

import com.koin.dependencyIntro.data.utils.Status

/*
We need a utility class that will be responsible to communicate the current state of Network Call to the UI Layer.
 We are naming that as Resource* */

data class Resource<out T>(val status: Status, val data: T?, val msg: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> Loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

}