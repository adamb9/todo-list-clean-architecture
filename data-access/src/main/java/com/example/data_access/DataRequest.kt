package com.example.data_access

import androidx.annotation.AnyThread
import androidx.annotation.WorkerThread
import com.example.entity.BusinessEntity
import java.io.IOException

interface DataRequest<R : BusinessEntity> {

    @WorkerThread
    @Throws(IOException::class)
    fun sync(): R

    @AnyThread
    fun async(callback: DataCallback<R>)

}