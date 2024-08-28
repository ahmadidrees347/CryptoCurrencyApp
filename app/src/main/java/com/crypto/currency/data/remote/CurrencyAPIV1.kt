package com.crypto.currency.data.remote

import com.crypto.currency.common.Constants
import com.crypto.currency.data.remote.dto.CurrencyDetailDto
import com.crypto.currency.data.remote.dto.CurrencyDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.HttpStatusCode

class CurrencyAPIV1(private val client: HttpClient) {

    suspend fun getAllCurrencies(): List<CurrencyDto> {
        return try {
            val response = client.get { url("${Constants.BASE_URL}/v1/coins") }
            if (response.status == HttpStatusCode.OK) {
                response.body()
            } else {
                println("Error: Failed to fetch getAllCurrencies: ${response.status}")
                emptyList()
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            emptyList()
        } catch (e: Exception) {
            println("Error: ${e.message}")
            emptyList()
        }
    }


    suspend fun getCurrencyById(currencyId: String): CurrencyDetailDto? {
        return try {
            val response = client.get { url("${Constants.BASE_URL}/v1/coins/$currencyId") }
            if (response.status == HttpStatusCode.OK) {
                response.body()
            } else {
                println("Error: Failed to fetch getCurrencyById: ${response.status}")
                null
            }
        } catch (e: RedirectResponseException) {
            // 3xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            // 4xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            // 5xx - responses
            println("Error: ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error: ${e.message}")
            null
        }
    }
}
