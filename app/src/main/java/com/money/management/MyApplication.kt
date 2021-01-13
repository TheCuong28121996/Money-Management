package com.money.management

import android.app.Application
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class MyApplication: Application() {
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate() {
        super.onCreate()

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        logEvent()
    }

    companion object {
        private var instance: MyApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    init {
        instance = this
    }

    private fun logEvent(){
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "001")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Cuong")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
        firebaseAnalytics.setUserProperty("favorite_food", "fasfasd")
    }
}