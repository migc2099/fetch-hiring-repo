package com.migc.fetchrewards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migc.fetchrewards.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getItems()
            items.forEach { println("${it.value}") }
        }
    }

}