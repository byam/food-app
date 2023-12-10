package com.example.foodapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.foodapp.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
            Toast.makeText(this, "Button Clicked!", Toast.LENGTH_LONG).show()
        }
    }
}
