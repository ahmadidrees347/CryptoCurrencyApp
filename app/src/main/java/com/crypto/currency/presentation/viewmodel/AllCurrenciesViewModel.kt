package com.crypto.currency.presentation.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crypto.currency.common.Resource
import com.crypto.currency.domain.usecase.GetAllCurrencyUseCase
import com.crypto.currency.presentation.model.CurrencyListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllCurrenciesViewModel @Inject constructor(
    private val getAllCurrencyUseCase: GetAllCurrencyUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CurrencyListState())
    val state: State<CurrencyListState> = _state

    init {
        getAllCurrency()
    }

    private fun getAllCurrency() {
        getAllCurrencyUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = CurrencyListState(currencyList = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CurrencyListState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = CurrencyListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}