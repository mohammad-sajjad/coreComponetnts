package com.core.corecomponents

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.core.corecomponents.databinding.ActivityMainBinding
import com.core.corecomponents.ui.BaseActivity


class MainActivity : BaseActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBinding = binding as ActivityMainBinding
        setupToolbar(ToolbarConfig("Home", true, {
            onBackPressedDispatcher.onBackPressed()
        }))

        mainBinding.button.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, HomeFragment.get(), "home fragment")
                .commit()
        }
    }

    override fun showToolbar() = true
}