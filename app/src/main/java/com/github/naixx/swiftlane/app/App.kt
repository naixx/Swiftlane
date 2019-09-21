package com.github.naixx.swiftlane.app

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerMainComponent.factory()
                .newMainComponent(AppModule(this)).inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = this.dispatchingAndroidInjector
}
