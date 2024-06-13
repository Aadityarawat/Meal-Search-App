package com.example.cleanarchitectureapp.presentation.meal_details

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureapp.common.Resource
import com.example.cleanarchitectureapp.domain.useCase.GetMealDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MealDetailViewModel @Inject constructor(private val repository: GetMealDetailsUseCase) : ViewModel() {

    private val _mealDetails = MutableStateFlow<MealDetailsState>(MealDetailsState())
    val mealDetails : StateFlow<MealDetailsState> = _mealDetails

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getMealDetails(id: String){
        repository(id).onEach {
            when(it){
                is Resource.Loading -> {
                    _mealDetails.value = MealDetailsState(isLoading = true)
                }
                is Resource.Success -> {
                    _mealDetails.value = MealDetailsState(data = it.data)
                }
                is Resource.Error -> {
                    _mealDetails.value = MealDetailsState(error = it.message?:"")
                }
            }
        }.launchIn(viewModelScope)
    }
}