package com.koin.dependencyIntro.module

/*
* Inside AppModule first, we will create the functions we want to provide as dependencies.
* */
import android.content.Context
import com.koin.dependencyIntro.BuildConfig
import com.koin.dependencyIntro.data.api.ApiHelper
import com.koin.dependencyIntro.data.api.ApiHelperImpl
import com.koin.dependencyIntro.data.api.ApiService
import com.koin.dependencyIntro.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
* To create the file as a supported module for Koin, we will pass the functions as singleton instance to the module.
The module is a collection of dependencies we are going to provide to the application. We will create a variable like,

*/
val appModule = module {
   // inside the module, we will pass the single instance of all the functions we created like
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() =
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }
    else OkHttpClient.Builder().build()

private fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)