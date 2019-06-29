package com.yeetsies.core.ui.screens.home

import com.yeetsies.core.R
import com.yeetsies.core.navigation.NavigationDestination
import com.yeetsies.core.ui.base.NavigationViewModel
import com.yeetsies.core.ui.screens.login.LoginDestination

class HomeViewModel : NavigationViewModel() {
    override val requiresAuth: Boolean = true
    private val loggedIn: Boolean = true

    init {
        if (requiresAuth && !loggedIn) {
            navigator.navigateTo(LoginDestination())
        }
    }
}

class HomeDestination : NavigationDestination(R.id.action_loginFragment_to_homeFragment)
