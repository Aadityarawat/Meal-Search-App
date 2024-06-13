package com.example.cleanarchitectureapp.presentation.meal_search

import com.example.cleanarchitectureapp.domain.model.Meal

data class MealSearchState(
    val data : List<Meal>? = null,
    val error : String = "",
    val isLoading : Boolean = false
)
