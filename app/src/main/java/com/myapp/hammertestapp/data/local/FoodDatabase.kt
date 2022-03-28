package com.myapp.hammertestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.hammertestapp.domain.model.Drink

@Database(entities = [Drink::class], version = 1)
abstract class FoodDatabase: RoomDatabase() {

    abstract val dao: FoodDao

}