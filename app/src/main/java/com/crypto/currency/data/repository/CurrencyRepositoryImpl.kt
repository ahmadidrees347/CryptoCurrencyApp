package com.crypto.currency.data.repository

import com.crypto.currency.data.remote.CurrencyAPI
import com.crypto.currency.data.remote.dto.toCurrency
import com.crypto.currency.data.remote.dto.toCurrencyDetail
import com.crypto.currency.domain.model.CurrencyDetailModel
import com.crypto.currency.domain.model.CurrencyModel
import com.crypto.currency.domain.repository.CurrencyRepository
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyAPI
) : CurrencyRepository {

    override suspend fun getAllCurrencies(): List<CurrencyModel> {
        return api.getAllCurrencies().map { it.toCurrency() }
    }

    override suspend fun getCurrencyById(currencyId: String): CurrencyDetailModel {
        return api.getCurrencyById(currencyId).toCurrencyDetail()
    }
}