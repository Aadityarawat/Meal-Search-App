package com.example.cleanarchitectureapp.presentation.meal_details

import com.example.cleanarchitectureapp.domain.model.Meal
import com.example.cleanarchitectureapp.domain.model.MealDetails

data class MealDetailsState(
    val data : MealDetails? = null,
    val error : String = "",
    val isLoading : Boolean = false
)
