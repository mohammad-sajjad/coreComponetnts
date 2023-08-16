package com.core.corecomponents

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.core.corecomponents.databinding.HomeFragmentLayoutBinding
import com.core.corecomponents.ui.BaseFragment

class HomeFragment: BaseFragment() {

    companion object {
        fun get() = HomeFragment()
    }

    override fun getTitle() = "home fragment"

    override fun getResourceFile() = R.layout.home_fragment_layout

    override fun bindView() {
        (fragmentBinding as HomeFragmentLayoutBinding).exit.setOnClickListener {
            Log.e(TAG, "bindView: clicked", )
        }
    }

}