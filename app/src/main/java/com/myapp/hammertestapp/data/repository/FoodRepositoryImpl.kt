package com.myapp.hammertestapp.data.repository

import android.util.Log
import com.myapp.hammertestapp.data.local.FoodDatabase
import com.myapp.hammertestapp.data.remote.FoodApi
import com.myapp.hammertestapp.domain.model.Drink
import com.myapp.hammertestapp.domain.repository.FoodRepository
import com.myapp.hammertestapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val api: FoodApi,
    private val db: FoodDatabase
): FoodRepository {

    override suspend fun getListOfFood(): Flow<Resource<List<Drink>>> = flow {

        emit(Resource.Loading())

        db.dao.getAllFood().collect {
            if(it != null && it.isNotEmpty()) emit(Resource.Success(data = it))
            else {
                try {
                    val result = api.getListOfFood()
                    db.dao.deleteFood()
                    db.dao.addFood(result.drinks)
                    emit(Resource.Success(result.drinks))
                } catch (e: HttpException) {
                    emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
                    Log.d("repo", "getListOfFood: ${e.localizedMessage}")
                } catch (e: HttpException) {
                    emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
                    Log.d("repo", "getListOfFood: ${e.localizedMessage}")
                } catch (e: Exception) {
                    emit(Resource.Error(message = e.localizedMessage ?: "Unknown error!"))
                    Log.d("repo", "getListOfFood: ${e.localizedMessage}")
                }
            }
        }


    }
}