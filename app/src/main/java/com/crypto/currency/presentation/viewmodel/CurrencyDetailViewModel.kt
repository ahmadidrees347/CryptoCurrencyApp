package com.crypto.currency.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.currency.common.Constants
import com.crypto.currency.common.Resource
import com.crypto.currency.domain.usecase.GetCurrencyDetailUseCase
import com.crypto.currency.presentation.model.CurrencyDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CurrencyDetailViewModel @Inject constructor(
    private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CurrencyDetailState())
    val state: State<CurrencyDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_CURRENCY_ID)?.let { currencyId ->
            getCurrencyDetail(currencyId)
        }
    }

    private fun getCurrencyDetail(currencyId: String) {
        getCurrencyDetailUseCase(currencyId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrencyDetailState(currencyModel = result.data)
                }
                is Resource.Error -> {
                    _state.value = CurrencyDetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrencyDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}