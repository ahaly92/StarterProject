package com.yeetsies.core.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent

class LiveNavigationField<T : Any> : LifecycleObserver {
    private var liveData = MutableLiveData<T>()

    private var lifecycleOwner: LifecycleOwner? = null

    var latestValue: T?
        get() = liveData.value
        set(value) {
            liveData.value = value
        }

    fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (lifecycleOwner != null) {
            throw IllegalStateException("Function 'observe' can only be called once during each lifecycle")
        }

        lifecycleOwner = owner.also {
            it.lifecycle.addObserver(this)
        }

        liveData.observe(owner, observer)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun ON_DESTROY() {
        lifecycleOwner?.let {
            lifecycleOwner = null
            liveData = MutableLiveData()
            it.lifecycle.removeObserver(this)
        }
    }
}
