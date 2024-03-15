package com.stevenlopez.block2.p1.wundersale.data

import com.stevenlopez.block2.p1.wundersale.data.model.CategoriesResponse
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    suspend fun getProductsList(): Flow<Result<List<CategoriesResponse>>>
}