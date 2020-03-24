package com.devcraft.di

import androidx.room.Room
import com.devcraft.data.BuildConfig
import com.devcraft.data.api.data_source.GenericNetSource
import com.devcraft.data.api.service.GenericApi
import com.devcraft.data.hasNetwork
import com.devcraft.data.repository.GenericRepositoryImpl
import com.devcraft.data.room.Database
import com.devcraft.data.room.data_source.GenericDbSource
import com.devcraft.domain.repository.GenericRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoriesModule = module {
    factory<GenericRepository> { GenericRepositoryImpl() }
}

val dataProvidersModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java, BuildConfig.DATABASE_NAME
        ).build()
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .cache(get<Cache>())
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(androidContext()))
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, max-age=" + 5
                    ).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .addInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single<Gson> {
        GsonBuilder()
            .setLenient()
            .create()
    }

    single<Cache> {
        val cacheSize = (5 * 1024 * 1024).toLong()
        Cache(androidContext().cacheDir, cacheSize)
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .client(get<OkHttpClient>())
            .addConverterFactory(GsonConverterFactory.create(get<Gson>())).build()
    }
}

val sourcesModule = module {
    single { get<Database>().genericDao() }
    single { get<Retrofit>().create(GenericApi::class.java) }
    single { GenericDbSource(get()) }
    single { GenericNetSource(get()) }
}

