package com.crypto.currency.domain.repository

import com.crypto.currency.domain.model.CurrencyModel
import com.crypto.currency.domain.model.CurrencyDetailModel

interface CurrencyRepository {

    suspend fun getAllCurrencies(): List<CurrencyModel>

    suspend fun getCurrencyById(currencyId: String): CurrencyDetailModel
}