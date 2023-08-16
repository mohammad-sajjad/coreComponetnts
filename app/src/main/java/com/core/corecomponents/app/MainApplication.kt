package com.core.corecomponents.app

import android.app.Application
import java.lang.ref.WeakReference

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppController.init(WeakReference(this))

    }
}