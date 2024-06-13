package com.example.cleanarchitectureapp.domain.useCase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.cleanarchitectureapp.common.Resource
import com.example.cleanarchitectureapp.data.model.toDomainMeal
import com.example.cleanarchitectureapp.domain.model.Meal
import com.example.cleanarchitectureapp.domain.repository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val repository: MealSearchRepository) {

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(s : String) : Flow<Resource<List<Meal>>> = flow {
        try {

            emit(Resource.Loading())

            val response = repository.getMealList(s)

            val list = if (response.meals.isNullOrEmpty()){
                emptyList<Meal>()
            }else{
                response.meals.map { it!!.toDomainMeal() }
            }

            emit(Resource.Success(data = list))


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