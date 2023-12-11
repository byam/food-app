package com.example.foodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.foodapp.adapter.ViewPagerAdapter
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
    }
}
