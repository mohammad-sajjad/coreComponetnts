package com.core.corecomponents.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.core.corecomponents.databinding.BaseFragmentLayoutBinding


typealias inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment: Fragment() {
    var TAG  = javaClass.name
    protected lateinit var baseActivity: BaseActivity
    protected lateinit var fragmentBinding: ViewDataBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = activity as BaseActivity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, getResourceFile(), container, false)
        baseActivity.mTextViewScreenTitle.text = getTitle()
        bindBundle()
        bindView()
        bindViewModel()
        (fragmentBinding as ViewDataBinding?)?.lifecycleOwner = viewLifecycleOwner
        return (fragmentBinding as ViewDataBinding?)?.root
    }

    open fun showLoader(title:String = "Please Wait...") {
//        try {
//            if (loader != null && loader?.isVisible == false) {
//                loader?.show(baseActivity.supportFragmentManager, "dialog")
//            }
//        } catch (exc: Exception) {
//            Logger.e(BASE_TAG, exc.message)
//        }
    }

    open fun dismissLoader() {
//        if (loader != null && loader!!.isVisible) {
//            loader?.dismissAllowingStateLoss()
//        }
    }


    abstract fun getTitle(): String
    abstract fun getResourceFile(): Int
    abstract fun bindView()

    open fun bindViewModel() {}
    open fun bindBundle() {}
    open fun onViewCreated() {}
}

