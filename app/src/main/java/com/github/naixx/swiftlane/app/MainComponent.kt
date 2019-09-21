package com.github.naixx.swiftlane.app

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class,
            ActivityModule::class
        ]
)
interface MainComponent : AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        fun newMainComponent(appModule: AppModule): MainComponent
    }
}
