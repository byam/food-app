package com.example.foodapp.adapter

import UserPreferences
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.Recipe
import com.example.foodapp.databinding.ItemRecipeBinding

class RecipeAdapter(private val context: Context, private var recipes: ArrayList<Recipe>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeViewHolder {
        val binding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.binding.recipeTitle.text = recipe.name
        holder.binding.recipeIngredients.text = recipe.ingredients
        holder.binding.recipeInstructions.text = recipe.instructions

        val userPreferences = UserPreferences(context)
        holder.binding.recipeUserName.text = userPreferences.getUserById(recipe.userId)!!.name
    }

    override fun getItemCount(): Int = recipes.size
}
