package com.yeetsies.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment<out VM : BaseViewModel> : Fragment() {
    protected abstract val layoutResourceId: Int

    abstract fun createViewModel(): VM

    @Suppress("UNCHECKED_CAST")
    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return createViewModel() as T
            }
        }
        ViewModelProviders.of(this, factory).get(createViewModel()::class.java)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }
}
