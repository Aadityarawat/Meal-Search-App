package com.example.cleanarchitectureapp.presentation.meal_details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.cleanarchitectureapp.R
import com.example.cleanarchitectureapp.databinding.FragmentMealDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealDetailsFragment : Fragment() {

    private val binding by lazy { FragmentMealDetailsBinding.inflate(layoutInflater) }
    private val viewModel  : MealDetailViewModel by viewModels()
    private val args : MealDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= binding.root

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args.mealId?.let {
            viewModel.getMealDetails(it)
        }

        lifecycle.coroutineScope.launchWhenCreated {
            viewModel.mealDetails.collect{
                if (it.isLoading){

                }
                if (it.error.isNullOrEmpty()){
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding.mealDetails = it
                }
            }
        }

        binding.detailsBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}