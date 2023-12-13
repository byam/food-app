package com.example.foodapp.fragment

import UserPreferences
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.RecipeAdapter
import com.example.foodapp.data.Recipe
import com.example.foodapp.databinding.FragmentRecipesBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

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

        val userPreferences = UserPreferences(requireContext())
        recipes = userPreferences.getRecipes()

        // Set recycler view adapter
        val recipeAdapter = RecipeAdapter(view.context, recipes)
        binding.recViewRecipes.adapter = recipeAdapter
        binding.recViewRecipes.layoutManager = LinearLayoutManager(view.context)

        // Add recipe button
        val fabNewRecipe: FloatingActionButton = binding.fabAddRecipe
        fabNewRecipe.setOnClickListener {
            showAddRecipeDialog()
        }
    }

    private fun showAddRecipeDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_recipe, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        val titleEditText = dialogView.findViewById<EditText>(R.id.editTextRecipeTitle)
        val ingredientsEditText = dialogView.findViewById<EditText>(R.id.editTextIngredients)
        val instructionsEditText = dialogView.findViewById<EditText>(R.id.editTextInstructions)
        val addButton = dialogView.findViewById<Button>(R.id.buttonAddRecipe)

        val currentUser = UserPreferences(requireContext()).getCurrentUser()

        addButton.setOnClickListener {
            val newRecipe = Recipe(
                id = recipes.size + 1,
                name = titleEditText.text.toString(),
                ingredients = ingredientsEditText.text.toString(),
                instructions = instructionsEditText.text.toString(),
                userId = currentUser!!.id
            )
            recipes.add(newRecipe)
            binding.recViewRecipes.adapter?.notifyDataSetChanged()
            Toast.makeText(this.context, "New Recipe '${newRecipe.name}' is added", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}
