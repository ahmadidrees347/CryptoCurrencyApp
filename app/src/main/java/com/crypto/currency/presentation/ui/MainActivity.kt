package com.crypto.currency.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.crypto.currency.common.Constants
import com.crypto.currency.databinding.ActivityMainBinding
import com.crypto.currency.presentation.adapter.AllCurrenciesAdapter
import com.crypto.currency.presentation.viewmodel.AllCurrenciesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val allCurrenciesViewModel by viewModels<AllCurrenciesViewModel>()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { AllCurrenciesAdapter(arrayListOf()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initAdapter()
        getAllCurrencies()
    }

    private fun initAdapter() {
        with(binding) {
            recyclerCurrencies.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerCurrencies.adapter = adapter
        }
        adapter.listener = {
            val intent = Intent(this@MainActivity, CurrencyDetailActivity::class.java)
            intent.putExtra(Constants.PARAM_CURRENCY_ID, it.id)
            startActivity(intent)
        }
    }

    private fun getAllCurrencies() {
        lifecycleScope.launch {
            allCurrenciesViewModel.state.collectLatest {
                if (it.currencyList.isNotEmpty()) {
                    adapter.currencyList.addAll(it.currencyList)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}