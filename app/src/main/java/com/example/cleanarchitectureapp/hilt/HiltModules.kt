package com.example.cleanarchitectureapp.hilt

import com.example.cleanarchitectureapp.common.Constant
import com.example.cleanarchitectureapp.data.remote.MealSearchAPI
import com.example.cleanarchitectureapp.data.repository.GetMealDetailsImpl
import com.example.cleanarchitectureapp.data.repository.GetMealListImpl
import com.example.cleanarchitectureapp.domain.repository.GetMealsDetailsRepository
import com.example.cleanarchitectureapp.domain.repository.MealSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    @Singleton
    fun providesMealSearchAPI(): MealSearchAPI{
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealSearchAPI::class.java)
    }

    @Provides
    fun providesMealSearchRepository(mealSearchAPI: MealSearchAPI) : MealSearchRepository{
        return GetMealListImpl(mealSearchAPI)
    }

    @Provides
    fun providesGetMealsDetailsRepository(mealSearchAPI: MealSearchAPI): GetMealsDetailsRepository{
        return GetMealDetailsImpl(mealSearchAPI)
    }
}