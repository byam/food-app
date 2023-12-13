package com.example.foodapp.fragment

import UserPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.foodapp.MainActivity
import com.example.foodapp.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        binding.btnSignIn.setOnClickListener {
            performSignIn()
        }

        return binding.root
    }

    private fun performSignIn() {
        val email = binding.editTextEmail.text.toString()
        val password = binding.editTextPassword.text.toString()

        val userPreferences = UserPreferences(requireContext())
        val users = userPreferences.getUsers()

        val user = users.find { it.email == email && it.password == password }

        if (user != null) {
            // User exists, set as current user
            userPreferences.saveCurrentUser(user)
            (activity as MainActivity).onUserSignedIn()
        } else {
            Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }
}
