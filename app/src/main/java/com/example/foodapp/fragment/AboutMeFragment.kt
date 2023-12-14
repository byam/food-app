package com.example.foodapp.fragment

import UserPreferences
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.foodapp.R
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

        // edit button
        binding.meBtnEdit.setOnClickListener {
            showEditDialog()
        }
    }

    private fun showEditDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit_user, null)
        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Edit Profile")

        val alertDialog = dialogBuilder.create()

        val userPreferences = UserPreferences(requireContext())
        val currentUser = userPreferences.getCurrentUser()

        // Use findViewById to reference the EditTexts in the dialog
        val editName = dialogView.findViewById<EditText>(R.id.edit_name)
        val editEmail = dialogView.findViewById<EditText>(R.id.edit_email)
        val editPhone = dialogView.findViewById<EditText>(R.id.edit_phone)
        val editBio = dialogView.findViewById<EditText>(R.id.edit_bio)

        // Pre-fill the EditTexts with current user data
        editName.setText(currentUser?.name)
        editEmail.setText(currentUser?.email)
        editPhone.setText(currentUser?.phone)
        editBio.setText(currentUser?.bio)

        // Handle the save button click
        dialogView.findViewById<Button>(R.id.edit_save_button).setOnClickListener {
            // Update currentUser object with new values
            currentUser?.let { user ->
                user.name = editName.text.toString()
                user.email = editEmail.text.toString()
                user.phone = editPhone.text.toString()
                user.bio = editBio.text.toString()

                // Save updated user
                userPreferences.saveCurrentUser(user)
                // Update UI in fragment
                updateUI()
                alertDialog.dismiss()
            }
        }

        alertDialog.show()
    }

    private fun updateUI() {
        val currentUser = UserPreferences(requireContext()).getCurrentUser()
        binding.meName.text = currentUser?.name
        binding.meBio.text = currentUser?.bio
    }
}
