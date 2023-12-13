package com.example.foodapp.adapter

import UserPreferences
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.Blog
import com.example.foodapp.databinding.ItemBlogBinding

class BlogAdapter(private val context: Context, private var blogs: ArrayList<Blog>): RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {
    class BlogViewHolder(val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlogViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        val blog = blogs[position]
        holder.binding.blogTitle.text = blog.title
        holder.binding.blogContent.text = blog.content
        holder.binding.blogImage.setImageResource(blog.imageUri!!)

        val userPreferences = UserPreferences(context)
        holder.binding.blogUserName.text = userPreferences.getUserById(blog.userId)!!.name
    }

    override fun getItemCount(): Int = blogs.size
}
