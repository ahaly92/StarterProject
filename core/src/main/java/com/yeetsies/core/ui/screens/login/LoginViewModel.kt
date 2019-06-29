package com.yeetsies.core.ui.screens.login

import androidx.lifecycle.MutableLiveData
import com.yeetsies.core.R
import com.yeetsies.core.navigation.NavigationDestination
import com.yeetsies.core.ui.base.NavigationViewModel
import com.yeetsies.core.ui.screens.home.HomeDestination

class LoginViewModel : NavigationViewModel() {
    override val requiresAuth: Boolean = false
    private val password = MutableLiveData<String>()
    private val accountNumber = MutableLiveData<String>()

    init {
        accountNumber.latestValue = ""
        password.latestValue = ""
    }

    fun onAirportSearchButtonClick() {
        navigator.navigateTo(HomeDestination())
    }
}

class LoginDestination : NavigationDestination(R.id.loginFragment)
