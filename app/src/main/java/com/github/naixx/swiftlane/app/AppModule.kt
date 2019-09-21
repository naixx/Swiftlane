package com.github.naixx.swiftlane.app

import android.app.Application
import android.content.Context
import com.github.naixx.swiftlane.BuildConfig
import com.github.naixx.swiftlane.network.AuthInterceptor
import com.github.naixx.swiftlane.network.PixbayApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    private val CACHE_SIZE = (50 * 1024 * 1024).toLong() // 50 MB

    @Singleton
    @Provides
    fun provideContext(): Context = app

    @Singleton
    @Provides
    internal fun provideOkhttp(cache: Cache, authInterceptor: AuthInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {

            builder.addInterceptor(
                    HttpLoggingInterceptor(/*PrettyPrintLogger()*/).setLevel(
                            HttpLoggingInterceptor.Level.BODY
                    )
            )
        }
        builder.addInterceptor(authInterceptor)
        builder.cache(cache)
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE)
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://pixabay.com/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) = retrofit.create(PixbayApi::class.java)
}
