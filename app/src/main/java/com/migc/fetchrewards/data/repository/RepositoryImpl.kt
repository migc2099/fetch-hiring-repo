package com.migc.fetchrewards.data.repository

import android.util.Log
import com.migc.fetchrewards.data.ItemDto
import com.migc.fetchrewards.data.Route.ITEMS_URL
import com.migc.fetchrewards.domain.repository.Repository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import kotlinx.serialization.json.Json

class RepositoryImpl(
    private val client: HttpClient
) : Repository {

    val LOG_TAG = RepositoryImpl::class.java.name

    override suspend fun getItems(): Map<Int, List<ItemDto>> {
        return try {
            val response = client.request(ITEMS_URL)
            val jsonString: String = response.body()
            val items: List<ItemDto> = Json.decodeFromString(jsonString)
            items.filterNot { it.name.isNullOrBlank() }
                .sortedWith(compareBy<ItemDto> { it.listId }.thenBy { it.name })
                .groupBy { it.listId }
        } catch (e: Exception) {
            Log.d(LOG_TAG, "Error ${e.message}")
            emptyMap()
        }
    }

}