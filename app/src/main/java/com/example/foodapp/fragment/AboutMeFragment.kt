package com.example.foodapp.fragment

import UserPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodapp.databinding.FragmentAboutMeBinding

class AboutMeFragment: Fragment(){
    private lateinit var binding: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPreferences = UserPreferences(requireContext())
        val currentUser = userPreferences.getCurrentUser()
        val recipes = userPreferences.getRecipes().filter { it.userId == currentUser!!.id }
        val blogs = userPreferences.getBlogs().filter { it.userId == currentUser!!.id }
        val plans = userPreferences.getMealPlans().filter { it.userId == currentUser!!.id }

        binding.meName.text = currentUser!!.name
        binding.meImage.setImageResource(currentUser.imageUri)
        binding.meRecipeNames.text = "Recipes: ${ recipes.joinToString(separator = ", ") { it.name } }"
        binding.meBlogsName.text = "Blogs: ${ blogs.joinToString(separator = ", ") { it.title } }"
        binding.mePlanDates.text = "Meal Plan dates: ${ plans.joinToString(separator = ", ") { it.date } }"
        binding.meBio.text = currentUser.bio
    }
}
