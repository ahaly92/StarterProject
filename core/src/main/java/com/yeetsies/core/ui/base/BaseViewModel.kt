package com.yeetsies.core.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected var <T : Any> LiveData<T>.latestValue: T?
        get() = value
        set(value) {
            (this as MutableLiveData<T>).value = value
        }
}
