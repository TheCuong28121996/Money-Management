package com.money.management.base

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.money.management.utils.CommonUtils

abstract class BaseActivity: AppCompatActivity() {

    private lateinit var mProgressDialog: Dialog

    open fun getLayoutResId(): Int = 0

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initData()

    abstract fun startObserve()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutResId())

        initView(savedInstanceState)

        initData()

        startObserve()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showLoadingDialog() {
        hideLoadingDialog()
        mProgressDialog = CommonUtils.showLoadingDialog(this)
    }

    fun hideLoadingDialog() {
        if (this::mProgressDialog.isInitialized && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }
}