package com.example.cleanarchitectureapp.presentation.meal_search

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.RequiresExtension
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import com.example.cleanarchitectureapp.databinding.FragmentMealSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MealSearchFragment : Fragment() {

    private val binding by lazy { FragmentMealSearchBinding.inflate(layoutInflater) }
    private val mealSearchViewModel : MealSearchViewModel by viewModels()
    private val mealSearchAdapter = MealSearchAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.searchMeal.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mealSearchViewModel.searchMealList(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
              return false
            }

        })

        binding.searchRecyclerView.apply {
            adapter = mealSearchAdapter
        }

        lifecycle.coroutineScope.launchWhenCreated {
            mealSearchViewModel.mealSearchState.collect{
                if (it.isLoading){
                    binding.progressMealSearch.visibility = View.VISIBLE
                }
                if (it.error.isNotEmpty()){
                    binding.progressMealSearch.visibility = View.GONE
                }

                it.data?.let {
                    binding.progressMealSearch.visibility = View.GONE
                    mealSearchAdapter.setContentList(it.toMutableList())
                }
            }
        }

        mealSearchAdapter.itemClickListener {
            findNavController().navigate(
                MealSearchFragmentDirections.actionMealSearchFragmentToMealDetailsFragment(
                    mealId = it.mealId
                )
            )
        }
    }


}