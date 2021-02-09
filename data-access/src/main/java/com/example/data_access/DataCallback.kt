package com.example.data_access

import com.example.entity.BusinessEntity

@Suppress("unused")
/**
 * Basic interface for an asynchronous data result.
 */
interface DataCallback<R : BusinessEntity> {

    fun onSuccess(result: R)

    fun onError(exception: Exception)

}