package com.myapp.hammertestapp.data.local

import androidx.room.*
import com.myapp.hammertestapp.domain.model.Drink
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM Drink")
    fun getAllFood(): Flow<List<Drink>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFood(food: List<Drink>)

    @Query("DELETE FROM Drink")
    suspend fun deleteFood()
}