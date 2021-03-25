package com.devcraft.di

import androidx.room.Room
import com.devcraft.data.BuildConfig
import com.devcraft.data.api.data_source.DataNetSource
import com.devcraft.data.api.data_source.GenericNetSource
import com.devcraft.data.api.service.GenericApi
import com.devcraft.data.api.service.ListDataApi
import com.devcraft.data.hasNetwork
import com.devcraft.data.repository.DataRepositoryImpl
import com.devcraft.data.repository.GenericRepositoryImpl
import com.devcraft.data.room.Database
import com.devcraft.data.room.data_source.GenericDbSource
import com.devcraft.domain.repository.DataRepository
import com.devcraft.domain.repository.GenericRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

val repositoriesModule = module {
    factory<GenericRepository> { GenericRepositoryImpl() }
    factory<DataRepository> { DataRepositoryImpl(get()) }
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
            .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
            .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
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
    single { get<Retrofit>().create(ListDataApi::class.java) }
    single { DataNetSource(get()) }
}

private val REWRITE_RESPONSE_INTERCEPTOR: Interceptor = object : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())
        val cacheControl = originalResponse.header("Cache-Control")
        return if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains(
                "no-cache"
            ) ||
            cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
        ) {
            originalResponse.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + 5000)
                .build()
        } else {
            originalResponse
        }
    }
}

private val REWRITE_RESPONSE_INTERCEPTOR_OFFLINE: Interceptor = object : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
            request = request.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public")
                .build()
        return chain.proceed(request)
    }
}

