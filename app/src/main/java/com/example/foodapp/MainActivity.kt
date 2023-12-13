package com.example.foodapp

import UserPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodapp.adapter.ViewPagerAdapter
import com.example.foodapp.data.User
import com.example.foodapp.databinding.ActivityMainBinding
import com.example.foodapp.fragment.AboutMeFragment
import com.example.foodapp.fragment.BlogFragment
import com.example.foodapp.fragment.ContactFragment
import com.example.foodapp.fragment.MealPlannerFragment
import com.example.foodapp.fragment.RecipesFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Save static users
        val userPreferences = UserPreferences(this)
        if (userPreferences.getUsers().isEmpty()) {
            // Default users
            val users = arrayListOf(
                User(1, "Harry Potter", "harry@example.com", "secret", "1234567890", R.drawable.ic_avatar_male),
                User(2, "Hermione Granger", "hermione@example.com", "secret", "1234567891", R.drawable.ic_avatar_female),
                User(3, "Ron Weasley", "ron@example.com", "secret", "1234567892", R.drawable.ic_avatar_male)
            )
            userPreferences.saveUsers(users)
        }

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
}
