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
            ),
            Recipe(
                id = 4,
                name = "Beef Stroganoff",
                ingredients = "Beef sirloin, onions, mushrooms, beef broth, sour cream, Dijon mustard, egg noodles",
                instructions = "Sauté beef, add onions and mushrooms, pour in broth, simmer, stir in sour cream and mustard, serve over noodles."
            ),
            Recipe(
                id = 5,
                name = "Chicken Tikka Masala",
                ingredients = "Chicken breast, yogurt, tikka masala paste, onions, canned tomatoes, cream, cilantro, rice",
                instructions = "Marinate chicken, grill until charred, sauté onions, add paste and tomatoes, simmer, mix in cream and chicken, serve with rice."
            ),
            Recipe(
                id = 6,
                name = "Vegetable Stir Fry",
                ingredients = "Broccoli, bell peppers, carrots, snap peas, soy sauce, ginger, garlic, rice or noodles",
                instructions = "Stir fry vegetables, add ginger and garlic, pour soy sauce, serve over rice or mixed with noodles."
            ),
            Recipe(
                id = 7,
                name = "Fish Tacos",
                ingredients = "White fish, cabbage slaw, flour tortillas, avocado, lime, cilantro, sour cream",
                instructions = "Season fish, grill, warm tortillas, assemble tacos with slaw, fish, avocado, a squeeze of lime, cilantro, and sour cream."
            ),
            Recipe(
                id = 8,
                name = "Butternut Squash Soup",
                ingredients = "Butternut squash, vegetable stock, onions, carrots, cream, nutmeg, cinnamon",
                instructions = "Roast squash, sauté onions and carrots, blend with stock until smooth, simmer, stir in cream, season with nutmeg and cinnamon."
            ),
            Recipe(
                id = 9,
                name = "Lamb Curry",
                ingredients = "Lamb shoulder, curry powder, coconut milk, tomatoes, onions, garlic, ginger, rice",
                instructions = "Brown lamb, sauté onions, garlic, ginger, add curry powder, tomatoes, coconut milk, simmer, serve with rice."
            ),
            Recipe(
                id = 10,
                name = "Quinoa Salad",
                ingredients = "Quinoa, cherry tomatoes, cucumbers, feta cheese, olives, lemon vinaigrette",
                instructions = "Cook quinoa, chop vegetables, mix all ingredients, dress with vinaigrette, chill before serving."
            ),
        )

        // Set recycler view adapter
        val recipeAdapter = RecipeAdapter(recipes)
        binding.recViewRecipes.adapter = recipeAdapter
        binding.recViewRecipes.layoutManager = LinearLayoutManager(view.context)
    }
}
