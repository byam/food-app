package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.MealPlan
import com.example.foodapp.databinding.ItemMealPlanBinding
import com.example.foodapp.util.ConvertorUtil

class MealPlanAdapter(private var mealPlans: ArrayList<MealPlan>): RecyclerView.Adapter<MealPlanAdapter.MealPlanViewHolder>() {
    class MealPlanViewHolder(val binding: ItemMealPlanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MealPlanViewHolder {
        val binding = ItemMealPlanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealPlanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealPlanViewHolder, position: Int) {
        val mealPlan = mealPlans[position]
        holder.binding.mealPlanWeekDay.text = ConvertorUtil.getWeekdayName(mealPlan.date)
        holder.binding.mealPlanDate.text = mealPlan.date
        holder.binding.mealPlanType.text = mealPlan.mealType
        holder.binding.mealPlanRecipe.text = mealPlan.recipe
    }

    override fun getItemCount(): Int = mealPlans.size
}
