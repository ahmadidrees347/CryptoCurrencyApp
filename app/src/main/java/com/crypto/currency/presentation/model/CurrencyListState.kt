package com.crypto.currency.presentation.model

import com.crypto.currency.domain.model.CurrencyModel

data class CurrencyListState(
    val isLoading: Boolean = false,
    val currencyList: List<CurrencyModel> = emptyList(),
    val error: String = ""
)
