package com.alavpa.meep.data

import com.alavpa.meep.data.api.Api
import com.alavpa.meep.data.api.RemoteDataSource
import com.alavpa.meep.domain.repository.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single { GsonBuilder().create() }
    single { httpLoggingInterceptor() }
    single { httpClient(get()) }
    single { api(get(), get()) }
    single { ApiDataSource(get()) } bind RemoteDataSource::class
    single { DataRepository(get()) } bind Repository::class
}

fun httpLoggingInterceptor(): HttpLoggingInterceptor = if (BuildConfig.DEBUG)
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
else HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)

fun httpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

fun api(client: OkHttpClient, gson: Gson): Api = Retrofit.Builder()
    .baseUrl(Api.BASE_URL)
    .client(client)
    .addConverterFactory(GsonConverterFactory.create(gson))
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()
    .create(Api::class.java)