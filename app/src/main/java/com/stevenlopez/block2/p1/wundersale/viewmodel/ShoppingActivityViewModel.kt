package com.stevenlopez.block2.p1.wundersale.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stevenlopez.block2.p1.wundersale.data.ProductsRepository
import com.stevenlopez.block2.p1.wundersale.data.model.ProductXX
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ShoppingActivityViewModel(
    private val productsRepository: ProductsRepository
): ViewModel() {

    private val _products = MutableStateFlow<List<ProductXX>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init{
        viewModelScope.launch {
            productsRepository.getProductsList()
        }
    }
}