package com.example.cleanarchitectureapp.data.repository

import com.example.cleanarchitectureapp.data.model.MealsDTO
import com.example.cleanarchitectureapp.data.remote.MealSearchAPI
import com.example.cleanarchitectureapp.domain.repository.MealSearchRepository

class GetMealListImpl(private val mealSearchAPI: MealSearchAPI) : MealSearchRepository {
    override suspend fun getMealList(s: String): MealsDTO {
        return mealSearchAPI.getMealList(s)
    }
}