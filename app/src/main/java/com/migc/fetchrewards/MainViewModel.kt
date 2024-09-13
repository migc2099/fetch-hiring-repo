package com.migc.fetchrewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.fetchrewards.data.ItemDto
import com.migc.fetchrewards.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _items: MutableStateFlow<Map<Int, List<ItemDto>>> = MutableStateFlow(emptyMap())
    val items: StateFlow<Map<Int, List<ItemDto>>> = _items.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _items.value = repository.getItems()
            _items.value.forEach { println("${it.value}") }
        }
    }

}