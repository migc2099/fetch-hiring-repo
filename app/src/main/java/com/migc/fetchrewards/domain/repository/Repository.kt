package com.migc.fetchrewards.domain.repository

import com.migc.fetchrewards.data.ItemDto

interface Repository {

    suspend fun getItems(): Map<Int, List<ItemDto>>

}