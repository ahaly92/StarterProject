package com.yeetsies.starterproject.screens.home

import android.os.Bundle
import android.view.View
import com.yeetsies.core.ui.base.BaseFragment
import com.yeetsies.core.ui.screens.home.HomeViewModel
import com.yeetsies.starterproject.R

class HomeFragment : BaseFragment<HomeViewModel>() {
    override val layoutResourceId: Int = R.layout.fragment_home
    override fun createViewModel(): HomeViewModel = HomeViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureViewModel()
    }

    private fun configureViewModel() {
        configureNavigationListener(viewModel.navigationLiveDataField)
    }
}
