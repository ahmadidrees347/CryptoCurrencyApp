package com.crypto.currency.presentation.model

import com.crypto.currency.domain.model.CurrencyDetailModel

data class CurrencyDetailState(
    val isLoading: Boolean = false,
    val currencyModel: CurrencyDetailModel? = null,
    val error: String = ""
)
