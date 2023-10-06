package com.crypto.currency.data.remote.dto


import com.crypto.currency.domain.model.CurrencyModel
import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("rank")
    val rank: Int,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)

fun CurrencyDto.toCurrency(): CurrencyModel {
    return CurrencyModel(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}