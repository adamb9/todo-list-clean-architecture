package com.example.data_access

import java.util.concurrent.ExecutorService
import javax.inject.Named


interface DataAccessComponent {

    companion object {
        /**
         * The singleton instance for [DataAccessComponent].
         * This is initialised by the `data-bridge` module and used by the `business` layer.
         * The instance can be replaced with a mock for testing when necessary.
         */
        @Volatile
        @JvmStatic
        lateinit var INSTANCE: DataAccessComponent

        const val GLOBAL_COMPUTATION_EXECUTOR = "computation_executor"
        const val GLOBAL_IO_EXECUTOR = "IO_executor"
    }

    fun taskRepo(): TaskRepo

    @Named(GLOBAL_IO_EXECUTOR)
    fun ioExecutor(): ExecutorService

    @Named(GLOBAL_COMPUTATION_EXECUTOR)
    fun computationExecutor(): ExecutorService

}