package com.crypto.currency.di

import com.crypto.currency.common.Constants
import com.crypto.currency.data.remote.CurrencyAPI
import com.crypto.currency.data.repository.CurrencyRepositoryImpl
import com.crypto.currency.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAPIService(): CurrencyAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyAPI): CurrencyRepository {
        return CurrencyRepositoryImpl(api)
    }
}