package com.crypto.currency.domain.model

data class CurrencyModel(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
