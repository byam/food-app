package com.example.foodapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.RecipeAdapter
import com.example.foodapp.data.Recipe
import com.example.foodapp.databinding.FragmentRecipesBinding

class RecipesFragment: Fragment() {
    private lateinit var binding: FragmentRecipesBinding
    private lateinit var recipes: ArrayList<Recipe>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Static recipes
        recipes = arrayListOf(
            Recipe(
                id = 1,
                name = "Classic Margherita Pizza",
                ingredients = "Dough, Tomato Sauce, Fresh Mozzarella, Basil Leaves, Olive Oil, Salt",
                instructions = "Roll out dough, apply sauce, add cheese, top with basil, drizzle olive oil, bake at 475°F for 10-12 mins."
            ),
            Recipe(
                id = 2,
                name = "Spaghetti Carbonara",
                ingredients = "Spaghetti, Pancetta, Eggs, Parmesan Cheese, Black Pepper",
                instructions = "Cook spaghetti, fry pancetta, mix eggs and cheese, combine with pasta, season with pepper."
            ),
            Recipe(
                id = 3,
                name = "Caesar Salad",
                ingredients = "Romaine Lettuce, Croutons, Parmesan Cheese, Caesar Dressing, Lemon Juice, Anchovy Paste",
                instructions = "Toss lettuce with dressing, add croutons and cheese, drizzle lemon juice, serve chilled."
            )
        )

        // Set recycler view adapter
        val recipeAdapter = RecipeAdapter(recipes)
        binding.recViewRecipes.adapter = recipeAdapter
        binding.recViewRecipes.layoutManager = LinearLayoutManager(view.context)
    }
}
