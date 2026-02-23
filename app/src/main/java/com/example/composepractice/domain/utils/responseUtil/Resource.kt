package com.example.composepractice.domain.utils.responseUtil

import com.example.composeproject.data.networkSection.responseUtil.Status

data class Resource<out T>(val status: Status, val data: T?, val error: AppError?) {
    companion object {
        fun <T> success(data: T? = null): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(error: AppError): Resource<T> {
            return Resource(Status.ERROR, null, error)
        }


        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}