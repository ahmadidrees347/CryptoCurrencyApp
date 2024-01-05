package com.crypto.currency.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.crypto.currency.databinding.ActivityCurrencyDetailBinding
import com.crypto.currency.presentation.viewmodel.CurrencyDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CurrencyDetailActivity : AppCompatActivity() {

    private val binding by lazy { ActivityCurrencyDetailBinding.inflate(layoutInflater) }
    private val currencyDetailViewModel by viewModels<CurrencyDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getCurrencyDetail()
    }

    private fun getCurrencyDetail() {
        lifecycleScope.launch {
            currencyDetailViewModel.state.collect {
                if (it.currencyModel != null) {
                    binding.txtTitle.text = it.currencyModel.name
                    binding.txtDescription.text = it.currencyModel.description
                    binding.txtTags.text = it.currencyModel.tags.joinToString()
                }
            }
        }
    }
}