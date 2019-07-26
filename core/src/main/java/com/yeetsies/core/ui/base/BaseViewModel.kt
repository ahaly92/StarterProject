package com.yeetsies.core.ui.base

import androidx.lifecycle.ViewModel
import com.yeetsies.core.navigation.LiveNavigationField
import com.yeetsies.core.navigation.NavigationEvent
import com.yeetsies.core.navigation.Navigator

abstract class BaseViewModel : ViewModel() {
    protected abstract val requiresAuth: Boolean
}

abstract class NavigationViewModel : BaseViewModel() {
    val navigationLiveDataField = LiveNavigationField<NavigationEvent>()
    val navigator = Navigator(this)
}
