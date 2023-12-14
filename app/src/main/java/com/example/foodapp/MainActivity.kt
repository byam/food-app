package com.example.foodapp

import UserPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodapp.adapter.ViewPagerAdapter
import com.example.foodapp.data.Blog
import com.example.foodapp.data.MealPlan
import com.example.foodapp.data.Recipe
import com.example.foodapp.data.User
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.fragment.AboutMeFragment
import com.example.foodapp.fragment.BlogFragment
import com.example.foodapp.fragment.ContactFragment
import com.example.foodapp.fragment.MealPlannerFragment
import com.example.foodapp.fragment.RecipesFragment
import com.example.foodapp.fragment.SignInFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isUserSignedIn: Boolean = false
    private lateinit var userPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Save static users, recipes, blogs, meal plans
        userPreferences = UserPreferences(this)
        userPreferences.clearAllData()
        if (userPreferences.getUsers().isEmpty()) {
            // Default users
            val users = arrayListOf(
                User(1, "Harry Potter", "harry@example.com", "secret", "1231231234", R.drawable.ic_avatar_male,
                    "A wizard with a knack for adventurous and bold flavors. Harry enjoys experimenting with traditional wizarding recipes and adding his own twist. Favorite recipe: Treacle Tart."),

                User(2, "Hermione Granger", "hermione@example.com", "secret", "1231231234", R.drawable.ic_avatar_female,
                    "An expert in potion-making, Hermione brings precision and meticulousness to her cooking. She loves creating dishes that are both nutritious and delicious. Favorite recipe: Pumpkin Pasties."),

                User(3, "Ron Weasley", "ron@example.com", "secret", "1234567892", R.drawable.ic_avatar_male,
                    "Ron's food philosophy is all about comfort and generosity. He has a special place in his heart for hearty, family-style meals. Favorite recipe: Cornish Pasties.")
            )
            userPreferences.saveUsers(users)
        }
        if (userPreferences.getRecipes().isEmpty()) {
            // Initialize and save Recipes
            val recipes = arrayListOf(
                Recipe(1, "Butterbeer", "Butter, brown sugar, water, cream soda",
                    "Begin by melting butter in a pot, then add brown sugar and stir until dissolved. Add water and let it simmer. Finally, mix in cream soda and serve warm for a delightful treat.",
                    1),
                Recipe(2, "Pumpkin Pasties", "Pumpkin puree, pie crust, cinnamon",
                    "Mix pumpkin puree with cinnamon to taste. Roll out the pie crust and cut into circles. Place a spoonful of the pumpkin mixture onto each circle, fold, and seal the edges. Bake until golden brown.",
                    1),
                Recipe(3, "Chocolate Frogs", "Chocolate, frog molds",
                    "First, temper the chocolate by melting it in a double boiler. Once smooth, pour the chocolate into frog-shaped molds. Allow them to cool until set, then gently remove from the molds.",
                    2),
                Recipe(4, "Cauldron Cakes", "Flour, sugar, cocoa powder",
                    "Combine flour, sugar, and cocoa powder in a mixing bowl. Gradually add wet ingredients and stir until the batter is smooth. Pour into cauldron-shaped molds and bake until a toothpick comes out clean.",
                    2),
                Recipe(5, "Treacle Tart", "Golden syrup, lemon, shortcrust pastry",
                    "Preheat the oven. Mix golden syrup with lemon juice. Line a tart tin with the pastry, pour in the syrup mixture, and bake until the filling is set and the pastry is golden.",
                    3),
                Recipe(6, "Cornish Pasties", "Pastry dough, beef, potatoes, swede",
                    "Cut beef, potatoes, and swede into small pieces and season well. Roll out the pastry dough and cut into rounds. Place the filling onto one side of each round, fold over, and crimp the edges. Bake until golden.",
                    3)
            )
            userPreferences.saveRecipes(recipes)
        }

        if (userPreferences.getMealPlans().isEmpty()) {
            // Initialize and save Meal Plans
            val mealPlans = arrayListOf(
                MealPlan(1, "2023-12-01", "Breakfast", "Butterbeer", 1),
                MealPlan(2, "2023-12-01", "Lunch", "Pumpkin Pasties", 1),
                MealPlan(3, "2023-12-01", "Dinner", "Cauldron Cakes", 1),
                MealPlan(4, "2023-12-02", "Breakfast", "Chocolate Frogs", 2),
                MealPlan(5, "2023-12-02", "Lunch", "Cornish Pasties", 2),
                MealPlan(6, "2023-12-02", "Dinner", "Treacle Tart", 2),
                MealPlan(7, "2023-12-03", "Breakfast", "Pumpkin Pasties", 3),
                MealPlan(8, "2023-12-03", "Lunch", "Butterbeer", 3),
                MealPlan(9, "2023-12-03", "Dinner", "Chocolate Frogs", 3)
            )
            userPreferences.saveMealPlans(mealPlans)
        }

        if(userPreferences.getBlogs().isEmpty()) {
            val blogs = arrayListOf(
                Blog(1, "Wizarding World Cuisine",
                    "Explore the magical tastes of Hogwarts with an in-depth look into the wizarding world's unique culinary delights. From the hearty meals served in the Great Hall to the whimsical treats found at Hogsmeade, discover how food enhances the magic of the wizarding world.",
                    R.drawable.hogwards, "2023-01-01", 1),

                Blog(2, "Potions in Cooking",
                    "Dive into the fascinating intersection of potions and cooking in the wizarding world. Learn how certain ingredients can bring a magical twist to your dishes. This blog explores the use of potion-making techniques in everyday cooking, revealing secrets behind some of the most enchanting meals.",
                    R.drawable.hogwards2, "2023-01-02", 2),

                Blog(3, "Feasts at Hogwarts",
                    "Reminisce about the grand feasts at the Great Hall of Hogwarts. This blog takes you on a journey through the lavish banquets, detailing the variety of dishes served during different occasions. Discover the stories behind the sumptuous spreads that bring the Hogwarts community together.",
                    R.drawable.hogwards3, "2023-01-03", 3)
            )
            userPreferences.saveBlogs(blogs)
        }

        // Check if user is signed in and update UI accordingly
        updateUIBasedOnAuthState()

        // Sign out button
        binding.btSignOut.setOnClickListener{
            onUserSignedOut()
        }
    }

    private fun updateUIBasedOnAuthState() {
        if (isUserSignedIn) {
            binding.fgSignIn.visibility = View.GONE // Hide the sign-in FrameLayout
            binding.tabLayout.visibility = View.VISIBLE // tab
            binding.viewpager.visibility = View.VISIBLE // Show the ViewPager
            binding.bottomNavigationView.visibility = View.VISIBLE // Show the Bottom Navi

            binding.helloUser.text = "Hello, ${userPreferences.getCurrentUser()?.name ?: "User"}"
            binding.helloUser.visibility = View.VISIBLE
            binding.btSignOut.visibility = View.VISIBLE

            setupMainContent()
        } else {
            binding.fgSignIn.visibility = View.VISIBLE // Show the sign-in FrameLayout
            binding.tabLayout.visibility = View.GONE // tab
            binding.viewpager.visibility = View.GONE // Hide the ViewPager
            binding.bottomNavigationView.visibility = View.GONE // Hide the Bottom Navi
            binding.helloUser.visibility = View.GONE
            binding.btSignOut.visibility = View.GONE
            showSignInFragment()
        }
    }

    private fun setupMainContent() {
        // Tabs and swipes
        val tabLayout: TabLayout = binding.tabLayout
        val viewPager: ViewPager2 = binding.viewpager
        val fragments: List<Fragment> = listOf(
            RecipesFragment(),
            MealPlannerFragment(),
            BlogFragment(),
            ContactFragment(),
            AboutMeFragment(),
        )
        val viewPagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Recipes"
                1 -> "Meal Planner"
                2 -> "Blog"
                3 -> "Contact"
                4 -> "About me"
                else -> "Tab ${position + 1}"
            }
        }.attach()

        // Bottom Navigation
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            binding.viewpager.currentItem = when (item.itemId) {
                R.id.bot_nav_recipes -> 0
                R.id.bot_nav_meal_planner -> 1
                R.id.bot_nav_blog -> 2
                else -> 0
            }
            true
        }

        // Sync ViewPager2 page changes with BottomNavigationView
        binding.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position < 3)
                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })
    }

    private fun showSignInFragment() {
        // Display the SignInFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fg_sign_in, SignInFragment())
            .commit()
    }

    fun onUserSignedIn() {
        isUserSignedIn = true
        updateUIBasedOnAuthState()
    }

    fun onUserSignedOut() {
        isUserSignedIn = false
        updateUIBasedOnAuthState()
    }
}
