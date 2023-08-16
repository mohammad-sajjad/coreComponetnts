package com.core.corecomponents.app

import android.content.Context
import java.lang.ref.WeakReference

class AppController: AppControllerContract {
    companion object {
        private var INSTANCE: AppController? = null
        private var context: WeakReference<Context>? = null

        fun init(context: WeakReference<Context>): AppController? {
            Companion.context = context
            INSTANCE = AppController()
            return INSTANCE
        }

        fun get(): AppController = INSTANCE ?: throw AppContextGoneException()
    }
}