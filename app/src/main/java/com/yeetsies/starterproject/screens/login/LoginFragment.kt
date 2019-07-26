package com.yeetsies.starterproject.screens.login

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.yeetsies.core.repository.login.LoginRepositoryImpl
import com.yeetsies.core.ui.base.BaseFragment
import com.yeetsies.core.ui.screens.login.LoginViewModel
import com.yeetsies.core.utils.afterTextChanged
import com.yeetsies.starterproject.R
import kotlinx.android.synthetic.main.fragment_login.accountNumber
import kotlinx.android.synthetic.main.fragment_login.loginButton
import kotlinx.android.synthetic.main.fragment_login.password

class LoginFragment : BaseFragment<LoginViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_login
    override fun createViewModel(): LoginViewModel = LoginViewModel(LoginRepositoryImpl())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewModel()
        configureUIListeners()
    }

    private fun configureUIListeners() {
        view?.apply {
            loginButton.setOnClickListener { viewModel.onLoginClick() }
            accountNumber.afterTextChanged {
                viewModel.accountNumber.value = it
            }
            password.afterTextChanged { viewModel.password.value = it }
        }
    }

    private fun configureViewModel() {
        viewModel.apply {
            configureNavigationListener(navigationLiveDataField)
            accountNumberError.observe(this@LoginFragment,
                Observer { it?.let { showErrorDialog(getString(it)) } })
            passwordError.observe(this@LoginFragment, Observer { it?.let { showErrorDialog(getString(it)) } })
            serverError.observe(this@LoginFragment, Observer {
                it?.let {
                    showErrorDialog(it)
                }
            })
        }

    }

    private fun showErrorDialog(message: String) {
        viewModel.clearErrors()
        AlertDialog.Builder(context)
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .apply { create() }.show()
    }
}
