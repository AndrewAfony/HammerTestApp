package com.myapp.hammertestapp.domain.repository

import com.myapp.hammertestapp.domain.model.Drink
import com.myapp.hammertestapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun getListOfFood(): Flow<Resource<List<Drink>>>
}