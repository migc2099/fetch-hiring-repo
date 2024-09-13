package com.migc.fetchrewards.data

import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val id: Int,
    val listId: Int,
    val name: String?
)
