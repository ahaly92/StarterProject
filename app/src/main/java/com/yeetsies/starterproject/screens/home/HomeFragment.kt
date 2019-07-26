package com.yeetsies.starterproject.screens.home

import android.os.Bundle
import android.view.View
import com.yeetsies.core.ui.base.BaseFragment
import com.yeetsies.core.ui.screens.home.HomeViewModel
import com.yeetsies.starterproject.R
import kotlinx.android.synthetic.main.fragment_home.restartTimer

class HomeFragment : BaseFragment<HomeViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_home
    override fun createViewModel(): HomeViewModel = HomeViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewModel()
        configureUIListeners()
    }

    private fun configureUIListeners() {
        view?.apply {
            restartTimer.setOnClickListener { viewModel.restartTimerClicked() }
        }
    }

    private fun configureViewModel() {
        configureNavigationListener(viewModel.navigationLiveDataField)
    }
}
