package com.crypto.currency.presentation.ui.currency_list

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.crypto.currency.common.Constants
import com.crypto.currency.presentation.ui.currency_detail.CurrencyDetailActivity
import com.crypto.currency.presentation.viewmodel.AllCurrenciesViewModel

@Composable
fun CurrencyListScreen(viewModel: AllCurrenciesViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.currencyList) { currency ->
                CurrencyListItem(
                    currency = currency,
                    onItemClick = {
                        val intent =
                            Intent(context, CurrencyDetailActivity::class.java)
                        intent.putExtra(Constants.PARAM_CURRENCY_ID, currency.id)
                        context.startActivity(intent)
                    }
                )
            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}