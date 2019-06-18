package com.example.testhoteles

import android.app.Application
import com.example.testhoteles.di.AppComponent
import com.example.testhoteles.di.ContextModule
import com.example.testhoteles.di.DaggerAppComponent

class MyApplication : Application(){

    var component: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()


    }
}