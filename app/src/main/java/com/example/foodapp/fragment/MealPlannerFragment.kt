package com.example.foodapp.fragment

import UserPreferences
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.MealPlanAdapter
import com.example.foodapp.data.MealPlan
import com.example.foodapp.databinding.FragmentMealPlannerBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar

class MealPlannerFragment: Fragment() {
    private lateinit var binding: FragmentMealPlannerBinding
    private lateinit var mealPlans: ArrayList<MealPlan>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMealPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Static recipes
        val userPreferences = UserPreferences(requireContext())
        mealPlans = userPreferences.getMealPlans()

        // Set recycler view adapter
        val recipeAdapter = MealPlanAdapter(view.context, mealPlans)
        binding.recViewMealPlan.adapter = recipeAdapter
        binding.recViewMealPlan.layoutManager = LinearLayoutManager(view.context)

        // Add recipe button
        val fabNewRecipe: FloatingActionButton = binding.fabAddMealPlan
        fabNewRecipe.setOnClickListener {
            showAddRecipeDialog()
        }
    }

    private fun showAddRecipeDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_meal_plan, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        val dateEditText = dialogView.findViewById<EditText>(R.id.editTextMealPlanDate)
        val mealTypeSpinner = dialogView.findViewById<Spinner>(R.id.spinnerMealPlanType)
        val recipeAutoComplete = dialogView.findViewById<AutoCompleteTextView>(R.id.autoCompleteMealPlanRecipe)
        val addButton = dialogView.findViewById<Button>(R.id.buttonAddMealPlan)

        // Setup date picker dialog
        dateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                dateEditText.setText("${selectedYear}-${selectedMonth + 1}-${selectedDayOfMonth}")
            }, year, month, day)

            datePickerDialog.show()
        }

        // Setup meal type spinner
        val mealTypes = resources.getStringArray(R.array.meal_types)
        mealTypeSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, mealTypes)

        // Setup recipe AutoCompleteTextView
        val recipes = resources.getStringArray(R.array.recipe_names)
        recipeAutoComplete.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, recipes))

        val currentUser = UserPreferences(requireContext()).getCurrentUser()

        addButton.setOnClickListener {
            val newMealPlan = MealPlan(
                id = mealPlans.size + 1,
                date = dateEditText.text.toString(),
                mealType = mealTypeSpinner.selectedItem.toString(),
                recipe = recipeAutoComplete.text.toString(),
                userId = currentUser!!.id,
            )
            mealPlans.add(newMealPlan)
            binding.recViewMealPlan.adapter?.notifyDataSetChanged()
            Toast.makeText(this.context, "New Plan '${newMealPlan.recipe}' is added", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }

}
