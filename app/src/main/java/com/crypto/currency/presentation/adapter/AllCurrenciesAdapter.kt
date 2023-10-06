package com.crypto.currency.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crypto.currency.databinding.ItemCurrencyBinding
import com.crypto.currency.domain.model.CurrencyModel

class AllCurrenciesAdapter(
    val currencyList: ArrayList<CurrencyModel>
) :
    RecyclerView.Adapter<AllCurrenciesAdapter.ViewHolder>() {

    var listener: ((CurrencyModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCurrencyBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currencyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currencyModel = currencyList[position]
        holder.bindData(currencyModel)
    }

    inner class ViewHolder(private val binding: ItemCurrencyBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(currencyModel: CurrencyModel) {
            with(binding) {
                txtTitle.text = currencyModel.name
                txtTitle.setOnClickListener { listener?.invoke(currencyModel) }
            }
        }
    }
}