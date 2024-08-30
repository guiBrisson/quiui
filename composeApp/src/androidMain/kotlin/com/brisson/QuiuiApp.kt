package com.brisson

import android.app.Application
import android.content.Context
import com.brisson.di.initKoin
import org.koin.dsl.module

class QuiuiApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            module {
                single<Context> { this@QuiuiApp }
            }
        )
    }
}
