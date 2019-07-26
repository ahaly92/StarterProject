package com.yeetsies.core.utils

import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.util.Date
import java.util.concurrent.TimeUnit

class InactivityLogoutManagerImpl : InactivityLogoutManager {

    companion object {
        private const val INACTIVITY_LOGOUT_TIME_SEC = 10L

        private var timerDisposable: Disposable? = null
        private val timerCompleteBehaviorSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()
        private var lastTimerReset: Date? = null
    }

    override fun stopTimer() {
        timerDisposable?.dispose()
        timerCompleteBehaviorSubject.onNext(false)
    }

    override fun startTimer() {
        restartTimer()
    }

    override fun restartTimer() {
        stopTimer()
        lastTimerReset = Date()
        timerDisposable = getTimer().subscribe()
    }

    override fun isBackgroundSessionTimedOut(): Boolean {
        lastTimerReset?.apply {
            return (Date().time - time) / 1000L > INACTIVITY_LOGOUT_TIME_SEC
        }
        return false
    }

    override fun onLogout() {
        stopTimer()
        lastTimerReset = null
    }

    override fun getTimerCompleteBehaviorSubject() = timerCompleteBehaviorSubject

    private fun getTimer(): Observable<Long> {
        return Observable.interval(1L, TimeUnit.SECONDS)
            .doOnNext {
                Timber.d((INACTIVITY_LOGOUT_TIME_SEC - it).toString())
            }.takeUntil { it == INACTIVITY_LOGOUT_TIME_SEC }.doOnComplete {
                timerCompleteBehaviorSubject.onNext(true)
            }
    }
}

interface InactivityLogoutManager {
    fun startTimer()
    fun restartTimer()
    fun stopTimer()
    fun isBackgroundSessionTimedOut(): Boolean
    fun onLogout()
    fun getTimerCompleteBehaviorSubject(): BehaviorSubject<Boolean>
}
