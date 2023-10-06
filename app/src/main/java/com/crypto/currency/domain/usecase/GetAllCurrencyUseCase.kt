package com.crypto.currency.domain.usecase

import com.crypto.currency.common.Resource
import com.crypto.currency.domain.model.CurrencyModel
import com.crypto.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCurrencyUseCase @Inject constructor(
    private val repository: CurrencyRepository
) {
    operator fun invoke(): Flow<Resource<List<CurrencyModel>>> = flow {
        try {
            emit(Resource.Loading())
            emit(Resource.Success(repository.getAllCurrencies()))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}