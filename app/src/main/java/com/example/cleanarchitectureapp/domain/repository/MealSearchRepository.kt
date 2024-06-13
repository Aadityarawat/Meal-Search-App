package com.example.cleanarchitectureapp.domain.repository

import com.example.cleanarchitectureapp.data.model.MealsDTO

interface MealSearchRepository {

    // returning MealsDTO because we are going to implement this function in data/repo folder
    suspend fun getMealList(s :String): MealsDTO

}