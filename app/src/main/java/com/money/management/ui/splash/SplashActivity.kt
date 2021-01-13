package com.money.management.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.core.content.res.ResourcesCompat
import com.money.management.ui.main.MainActivity
import com.money.management.R
import com.money.management.base.BaseActivity
import kotlinx.android.synthetic.main.splash_activity.*
import kotlinx.coroutines.*

class SplashActivity: BaseActivity() {
    val activityScope = CoroutineScope(Dispatchers.Main)

    override fun getLayoutResId(): Int {
        return R.layout.splash_activity
    }
    override fun initView(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val typeface = ResourcesCompat.getFont(this, R.font.script_mt_bold)
        tv_title.setTypeface(typeface)
    }

    override fun initData() {
        activityScope.launch {
            delay(3000)

            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    override fun startObserve() {

    }
}