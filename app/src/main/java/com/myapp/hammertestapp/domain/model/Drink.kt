package com.myapp.hammertestapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Drink(
    @PrimaryKey val strDrink: String,
    val strDrinkThumb: String?,
    val strInstructions: String?
)