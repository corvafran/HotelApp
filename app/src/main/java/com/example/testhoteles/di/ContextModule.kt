package com.example.testhoteles.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ContextModule(val context: Context) {

    @Provides
    fun obtainContext(): Context {
        return context
    }
}