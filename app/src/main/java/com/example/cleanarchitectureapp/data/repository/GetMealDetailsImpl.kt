package com.example.cleanarchitectureapp.data.repository

import com.example.cleanarchitectureapp.data.model.MealsDTO
import com.example.cleanarchitectureapp.data.remote.MealSearchAPI
import com.example.cleanarchitectureapp.domain.repository.GetMealsDetailsRepository

class GetMealDetailsImpl(private val mealSearchAPI : MealSearchAPI): GetMealsDetailsRepository {
    override suspend fun getMealsDetails(id: String): MealsDTO {
        return mealSearchAPI.getMealDetails(id)
    }
}