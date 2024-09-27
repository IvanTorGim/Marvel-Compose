package com.example.marvelcompose.data.network

import com.example.marvelcompose.data.network.remote.CharactersService
import com.example.marvelcompose.data.network.remote.ComicsService
import com.example.marvelcompose.data.network.remote.EventsService
import com.example.marvelcompose.data.network.remote.QueryInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @ApiEndPoint
    fun providesApiEndPoint(): String = "https://gateway.marvel.com/"

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        queryInterceptor: QueryInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(queryInterceptor)
        .build()

    @Provides
    fun providesResAdapter(
        @ApiEndPoint apiEndPoint: String,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiEndPoint)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun providesCharacterService(resAdapter: Retrofit): CharactersService = resAdapter.create()

    @Provides
    fun providesComicsService(resAdapter: Retrofit): ComicsService = resAdapter.create()

    @Provides
    fun providesEventsService(resAdapter: Retrofit): EventsService = resAdapter.create()
}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ApiEndPoint