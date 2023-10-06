package com.crypto.currency.data.remote

import com.crypto.currency.data.remote.dto.CurrencyDetailDto
import com.crypto.currency.data.remote.dto.CurrencyDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyAPI {

    @GET("/v1/coins")
    suspend fun getAllCurrencies(): List<CurrencyDto>

    @GET("/v1/coins/{id}")
    suspend fun getCurrencyById(@Path("id") currencyId: String): CurrencyDetailDto
}