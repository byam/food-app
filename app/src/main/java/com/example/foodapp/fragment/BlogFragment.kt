package com.example.foodapp.fragment

import UserPreferences
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.BlogAdapter
import com.example.foodapp.data.Blog
import com.example.foodapp.databinding.FragmentBlogBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Date

class BlogFragment: Fragment(){
    private lateinit var binding: FragmentBlogBinding
    private lateinit var blogs: ArrayList<Blog>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPreferences = UserPreferences(requireContext())
        blogs = userPreferences.getBlogs()

        // Set recycler view adapter
        val blogAdapter = BlogAdapter(view.context, blogs)
        binding.recViewBlogs.adapter = blogAdapter
        binding.recViewBlogs.layoutManager = LinearLayoutManager(view.context)

        // Add blog button
        val fabNewBlog: FloatingActionButton = binding.fabAddBlog
        fabNewBlog.setOnClickListener {
            showAddBlogDialog()
        }
    }

    private fun showAddBlogDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_add_blog, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val dialog = builder.create()

        val titleEditText = dialogView.findViewById<EditText>(R.id.add_blog_title)
        val contentEditText = dialogView.findViewById<EditText>(R.id.add_blog_content)
        val addButton = dialogView.findViewById<Button>(R.id.add_blog_button)

        val currentUser = UserPreferences(requireContext()).getCurrentUser()

        addButton.setOnClickListener {
            val newBlog = Blog(
                id = blogs.size + 1,
                title = titleEditText.text.toString(),
                content = contentEditText.text.toString(),
                date = Date().toString(),
                imageUri = R.drawable.hogwards,
                userId = currentUser!!.id
            )
            blogs.add(newBlog)
            binding.recViewBlogs.adapter?.notifyDataSetChanged()
            Toast.makeText(this.context, "New Blog '${newBlog.title}' is added", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }
}
