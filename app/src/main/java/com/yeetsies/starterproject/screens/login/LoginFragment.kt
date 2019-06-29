package com.yeetsies.starterproject.screens.login

import android.os.Bundle
import android.view.View
import com.yeetsies.core.ui.base.BaseFragment
import com.yeetsies.core.ui.screens.login.LoginViewModel
import com.yeetsies.starterproject.R
import kotlinx.android.synthetic.main.fragment_login.loginButton

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_login
    override fun createViewModel(): LoginViewModel = LoginViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewModel()
        configureUIListeners()
    }

    private fun configureViewModel() {
        configureNavigationListener(viewModel.navigationLiveDataField)
    }

    private fun configureUIListeners() {
        loginButton.setOnClickListener { viewModel.onAirportSearchButtonClick() }
    }
}
