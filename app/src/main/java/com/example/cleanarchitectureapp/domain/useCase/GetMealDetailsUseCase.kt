package com.example.cleanarchitectureapp.domain.useCase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cleanarchitectureapp.common.Resource
import com.example.cleanarchitectureapp.data.model.toDomainMeal
import com.example.cleanarchitectureapp.data.model.toDomainMealDetails
import com.example.cleanarchitectureapp.domain.model.Meal
import com.example.cleanarchitectureapp.domain.model.MealDetails
import com.example.cleanarchitectureapp.domain.repository.GetMealsDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val repository: GetMealsDetailsRepository) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(id : String): Flow<Resource<MealDetails>> = flow {

        try {

            emit(Resource.Loading())

            val response = repository.getMealsDetails(id).meals?.get(0)?.toDomainMealDetails()

            emit(Resource.Success(data = response!!))


        }catch (e : HttpException){
            emit(Resource.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
        catch (e: IOException){
            emit(Resource.Error(message = e.localizedMessage ?: "Check Your Internet Connection"))
        }
        catch (e : Exception){
            emit(Resource.Error(message = e.localizedMessage ?: "Maybe Empty"))
        }

    }
}