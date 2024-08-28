package com.crypto.currency.di

import com.crypto.currency.BuildConfig
import com.crypto.currency.data.remote.CurrencyAPIV1
import com.crypto.currency.data.repository.CurrencyRepositoryImpl
import com.crypto.currency.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import java.text.DateFormat
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideAPIServiceV1(): CurrencyAPI {
//        return Retrofit.Builder()
//            .baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(CurrencyAPI::class.java)
//    }


    class ApiLogger : Logger {
        override fun log(message: String) {
            println(message)
        }
    }

    @Provides
    @Singleton
    fun provideAPIService(): CurrencyAPIV1 {
        val client = HttpClient(CIO) {
            engine {
                requestTimeout = 30000
                maxConnectionsCount = 100
            }
            install(ContentNegotiation) {
                gson {
                    setDateFormat(DateFormat.LONG)
                    setPrettyPrinting()
                }
            }
            install(Logging) {
                logger = ApiLogger()//Logger.Companion.DEFAULT
                level = if (BuildConfig.DEBUG) {
                    LogLevel.ALL
                } else {
                    LogLevel.NONE
                }
            }
        }
        return CurrencyAPIV1(client)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyAPIV1): CurrencyRepository {
        return CurrencyRepositoryImpl(api)
    }
}