package com.yeetsies.core.navigation

import android.os.Bundle
import com.yeetsies.core.ui.base.NavigationViewModel

class Navigator(private val viewModel: NavigationViewModel) {
    fun navigateTo(destination: NavigationDestination) {
        viewModel.navigationLiveDataField.latestValue = destination.buildEvent()
    }
}

open class NavigationDestination(private val id: Int, private val navigationArguments: NavigationArguments? = null) {
    fun buildEvent(): NavigationEvent = NavigationEvent(id, navigationArguments)
}

interface NavigationArguments {
    fun getString(key: String): String?
    fun putString(key: String, value: String): NavigationArguments
    fun getBundle(): Bundle? = null
}

interface NavigationArgumentsFactory {
    fun getArgs(): NavigationArguments
}

var globalArgumentsFactory: NavigationArgumentsFactory = object : NavigationArgumentsFactory {
    override fun getArgs(): NavigationArguments {
        return BundleArgs(Bundle())
    }
}

class BundleArgs(delegateBundle: Bundle?) : NavigationArguments {
    private val bundle = delegateBundle ?: Bundle()

    override fun getString(key: String): String? = bundle.getString(key)
    override fun putString(key: String, value: String): NavigationArguments {
        bundle.putString(key, value)
        return this
    }

    override fun getBundle() = bundle
}
