package com.example.foodapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodapp.databinding.FragmentMealPlannerBinding

class MealPlannerFragment: Fragment() {
    private lateinit var binding: FragmentMealPlannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }
}
