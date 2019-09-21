package com.github.naixx.swiftlane.app

import com.github.naixx.swiftlane.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {
    @ContributesAndroidInjector
    fun contributeAccountHistoryActivityInjector(): MainActivity
}
