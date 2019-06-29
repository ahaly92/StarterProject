package com.yeetsies.core.logging

import android.util.Log
import com.yeetsies.core.BuildConfig
import timber.log.Timber

private const val DEFAULT_TAG = "Timber"

object TimberConfig {
    fun init() {
        val tree: Timber.Tree? = if (BuildConfig.LOG_CONSOLE) {
            Timber.DebugTree()
        } else {
            object : Timber.Tree() {
                override fun isLoggable(tag: String?, priority: Int): Boolean = (priority >= Log.INFO)

                override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
                    Log.println(priority, tag ?: DEFAULT_TAG, message)
                    throwable?.let {
                        Log.println(priority, tag ?: DEFAULT_TAG, Log.getStackTraceString(throwable))
                    }
                }
            }
        }
        tree?.let {
            Timber.plant(it)
        }
    }
}
