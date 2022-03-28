package com.myapp.hammertestapp.di

import android.app.Application
import androidx.room.Room
import com.myapp.hammertestapp.data.local.FoodDatabase
import com.myapp.hammertestapp.data.remote.FoodApi
import com.myapp.hammertestapp.data.repository.FoodRepositoryImpl
import com.myapp.hammertestapp.domain.repository.FoodRepository
import com.myapp.hammertestapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideFoodApi(): FoodApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(FoodApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFoodRepository(api: FoodApi, db: FoodDatabase): FoodRepository {
        return FoodRepositoryImpl(api, db)
    }

    @Singleton
    @Provides
    fun provideFoodDatabase(app: Application): FoodDatabase {
        return Room.databaseBuilder(
            app,
            FoodDatabase::class.java,
            "food_db"
        ).build()
    }
}