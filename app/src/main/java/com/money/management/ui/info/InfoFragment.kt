package com.money.management.ui.info

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProviders
import com.money.management.R
import com.money.management.base.BaseFragment
import com.money.management.widget.ItemInfo
import kotlinx.android.synthetic.main.info_fragment.*

class InfoFragment : BaseFragment() {

    private lateinit var viewModel: InfoViewModel
    private var count: Int = -1

    override fun getLayoutResId(): Int {
        return R.layout.info_fragment
    }

    override fun initView() {

        if (!this::viewModel.isInitialized) {
            viewModel = ViewModelProviders.of(this)
                .get(InfoViewModel::class.java)
            setObserveLive(viewModel)
        }

        item_theme.setOnCheckedChangedListener(object : ItemInfo.OnChangedListener {
            override fun onCheckedChanged(isChecked: Boolean) {
                if(0 <= count){
                    viewModel.toggleNightMode()
                }
            }
        })
    }

    override fun initData() {

    }

    override fun startObserve() {
        viewModel.repoMode.observe(this){
            item_theme.setChecked(it)
            setMode(it)
            count++
        }
    }

    private fun setMode(nightModeActive: Boolean){
        val defaultMode = if (nightModeActive) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(defaultMode)
    }
}