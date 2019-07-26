package com.yeetsies.core.ui.screens.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yeetsies.core.R
import com.yeetsies.core.navigation.NavigationDestination
import com.yeetsies.core.repository.login.LoginRepository
import com.yeetsies.core.rest.Result
import com.yeetsies.core.utils.InactivityLogoutManagerImpl
import com.yeetsies.core.ui.base.NavigationViewModel
import com.yeetsies.core.ui.screens.home.HomeDestination

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val inactivityLogoutManagerImpl: InactivityLogoutManagerImpl = InactivityLogoutManagerImpl()
) : NavigationViewModel() {
    override val requiresAuth: Boolean = false

    val password = MutableLiveData<String>()
    val accountNumber = MutableLiveData<String>()

    private val accountNumberErrorDataField = MutableLiveData<@StringRes Int>()
    val accountNumberError: LiveData<Int> = accountNumberErrorDataField

    private val passwordErrorDataField = MutableLiveData<@StringRes Int>()
    val passwordError: LiveData<Int> = passwordErrorDataField

    private val serverErrorDataField = MutableLiveData<String>()
    val serverError: LiveData<String> = serverErrorDataField

    fun onLoginClick() {
        if (validateUI()) {
            when (val result = loginRepository.login(accountNumber.value.orEmpty(), password.value.orEmpty())) {
                is Result.Error -> {
                    serverErrorDataField.value = result.exception.message.toString()
                }
                is Result.Success -> {
                    navigator.navigateTo(HomeDestination())
                    inactivityLogoutManagerImpl.startTimer()
                }
            }
        }
    }

    fun clearErrors() {
        passwordErrorDataField.value = null
        accountNumberErrorDataField.value = null
        serverErrorDataField.value = null
    }

    private fun validateUI(): Boolean {
        return when {
            accountNumber.value.isNullOrEmpty() -> {
                accountNumberErrorDataField.value = R.string.empty_account_number_error
                false
            }
            password.value.isNullOrEmpty() -> {
                passwordErrorDataField.value = R.string.empty_password_error
                false
            }
            else -> true
        }
    }
}

class LoginDestination : NavigationDestination(R.id.loginFragment)
