package com.money.management.ui.info

import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import com.money.management.R
import com.money.management.base.BaseFragment
import kotlinx.android.synthetic.main.info_fragment.*

class InfoFragment: BaseFragment() {
    override fun getLayoutResId(): Int {
        return R.layout.info_fragment
    }

    override fun initView() {
        switch_theme.setOnCheckedChangeListener { buttonView: CompoundButton?, isChecked: Boolean ->
            val defaultMode = if (isChecked) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(defaultMode)
        }
    }

    override fun initData() {

    }

    override fun startObserve() {

    }
}