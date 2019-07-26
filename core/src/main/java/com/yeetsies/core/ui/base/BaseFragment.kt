package com.yeetsies.core.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.yeetsies.core.R
import com.yeetsies.core.navigation.LiveNavigationField
import com.yeetsies.core.navigation.NavigationEvent
import com.yeetsies.core.utils.InactivityLogoutManagerImpl
import io.reactivex.disposables.Disposable

abstract class BaseFragment<out VM : BaseViewModel>(
    private val inactivityLogoutManagerImpl: InactivityLogoutManagerImpl = InactivityLogoutManagerImpl()
) : Fragment() {
    protected abstract val layoutResourceId: Int

    private lateinit var timerDisposable: Disposable

    abstract fun createViewModel(): VM

    @Suppress("UNCHECKED_CAST")
    protected val viewModel: VM by lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return createViewModel() as T
            }
        }
        ViewModelProviders.of(this, factory).get(createViewModel()::class.java)
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResourceId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.requiresAuth) {
            timerDisposable = inactivityLogoutManagerImpl.getTimerCompleteBehaviorSubject().subscribe {
                if (it) {
                    navigate(NavigationEvent(R.id.loginFragment))
                    InactivityLogoutManagerImpl().onLogout()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        timerDisposable.dispose()
    }

    protected fun configureNavigationListener(navigationLiveDataField: LiveNavigationField<NavigationEvent>) {
        navigationLiveDataField.observe(viewLifecycleOwner, Observer { navigate(it) })
    }

    private fun navigate(event: NavigationEvent) {
        view?.let {
            Navigation.findNavController(it).navigate(event.navId, event.argumentsBundle())
        }
    }
}

