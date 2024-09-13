package com.migc.fetchrewards

import android.app.Application
import com.migc.fetchrewards.di.fetchModule
import org.koin.core.context.startKoin

class FetchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(fetchModule)
        }
    }

}