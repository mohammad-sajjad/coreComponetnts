package com.core.corecomponents.ui

import android.app.Dialog
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.core.corecomponents.R
import com.core.corecomponents.databinding.ActivityBaseBinding
import com.core.corecomponents.network.ConnectivityObserver
import com.core.corecomponents.network.NetworkConnectivityManager
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.TimerTask


abstract class BaseActivity : AppCompatActivity() {
    var binding: ViewBinding? = null
    private var baseBinding: ActivityBaseBinding? = null
    lateinit var mTextViewScreenTitle: TextView
    private lateinit var mImageButtonBack: ImageButton
    private lateinit var menuMoreBtn: ImageButton
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        connectivityObserver = NetworkConnectivityManager(this)
        connectivityObserver.observer().onEach {
            Log.e(localClassName, "onCreate: ${it}", )
        }.launchIn(lifecycleScope)


    }

    override fun setContentView(layoutResID: Int) {
        baseBinding = ActivityBaseBinding.inflate(layoutInflater)
        val coordinatorLayout = baseBinding?.root
        val toolbar = baseBinding?.baseToolbar
        mTextViewScreenTitle = baseBinding?.fakeToolbar?.textScreenTitle!!
        mImageButtonBack = baseBinding?.fakeToolbar?.imageBackButton!!
        menuMoreBtn = baseBinding?.fakeToolbar?.imageMoreBtn!!
        toolbar?.visibility = if(showToolbar()) View.VISIBLE else View.GONE
        binding = DataBindingUtil.inflate(layoutInflater, layoutResID, baseBinding?.layoutContainer!!, true)
        baseBinding?.layoutContainer?.requestLayout()

        super.setContentView(coordinatorLayout)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(ContentValues.TAG, "onDestroy: ")
//        loader = null
    }

    override fun onPause() {
        super.onPause()
        Log.e(ContentValues.TAG, "onPause: ")
//        if(loader?.isShowing == true) {
//            loader?.dismiss()
//        }

    }

    override fun onStop() {
        super.onStop()
        Log.e(ContentValues.TAG, "onStop: ")
    }

    override fun onResume() {
        super.onResume()
        Log.e(ContentValues.TAG, "onResume: ")
    }

    open fun showLoader(title:String = "Please Wait...") {
//        loader?.setLabel(title)
//        loader?.show()
    }

    open fun dismissLoader() {
//        if(loader?.isShowing == true) {
//            loader?.dismiss()
//        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(ContentValues.TAG, "onStart: ")
    }

//    abstract fun inflateLayout(
//        parent: FrameLayout,
//        inflater: LayoutInflater
//    ): ViewBinding

    open fun showToolbar(): Boolean = true

    fun setupToolbar(toolbarConfig: ToolbarConfig) {
        mTextViewScreenTitle.text = toolbarConfig.title
        mImageButtonBack.setOnClickListener(toolbarConfig.onClick)
        mImageButtonBack.visibility = if(toolbarConfig.showBackButton) View.VISIBLE else View.GONE
        menuMoreBtn.visibility = if(toolbarConfig.showMenuButton) View.VISIBLE else View.GONE
        if(toolbarConfig.menuBtnConfig?.iconSrc != null) {
            menuMoreBtn.setImageDrawable(resources.getDrawable(toolbarConfig.menuBtnConfig?.iconSrc!!))
        }

        menuMoreBtn.setOnClickListener(toolbarConfig.menuBtnConfig?.onClick)
    }




    data class ToolbarMenuConfig(val iconSrc: Int,
                                 val onClick: View.OnClickListener)
    data class ToolbarConfig(val title: String = "",
                             var showBackButton: Boolean = true,
                             var onClick: View.OnClickListener? = null,
                             var showMenuButton: Boolean = false,
                             var menuBtnConfig: ToolbarMenuConfig? = null)
}