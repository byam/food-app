package com.example.foodapp.fragment

import UserPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.adapter.ContactAdapter
import com.example.foodapp.databinding.FragmentContactBinding

class ContactFragment: Fragment(){
    private lateinit var binding: FragmentContactBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPreferences = UserPreferences(requireContext())
        val users = userPreferences.getUsers()

        // Set recycler view adapter
        val recipeAdapter = ContactAdapter(users)
        binding.recViewContact.adapter = recipeAdapter
        binding.recViewContact.layoutManager = LinearLayoutManager(view.context)
    }
}
