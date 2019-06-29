package com.yeetsies.starterproject

import android.app.Application
import com.yeetsies.core.logging.TimberConfig

class StarterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        TimberConfig.init()
    }
}
