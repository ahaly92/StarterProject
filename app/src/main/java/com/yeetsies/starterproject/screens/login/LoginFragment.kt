package com.yeetsies.starterproject.screens.login

import com.yeetsies.core.ui.base.BaseFragment
import com.yeetsies.core.ui.screens.login.LoginViewModel
import com.yeetsies.starterproject.R

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_login
    override fun createViewModel(): LoginViewModel = LoginViewModel()

}
