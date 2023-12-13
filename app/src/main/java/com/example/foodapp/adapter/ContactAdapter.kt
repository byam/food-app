package com.example.foodapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.User
import com.example.foodapp.databinding.ItemContactBinding

class ContactAdapter(private var users: ArrayList<User>): RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    class ContactViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val user = users[position]
        holder.binding.contactUserName.text = user.name
        holder.binding.contactUserImage.setImageResource(user.imageUri)
        holder.binding.contactEmailImage.contentDescription = user.email
        holder.binding.contactPhoneImage.contentDescription = user.phone

        holder.binding.contactEmailImage.setOnClickListener {
            emailUser(holder.itemView.context, user.email)
        }

        holder.binding.contactPhoneImage.setOnClickListener {
            dialUser(holder.itemView.context, user.phone)
        }

        holder.binding.contactMessageImage.setOnClickListener {
            messageUser(holder.itemView.context, user.phone)
        }
    }

    private fun emailUser(context: Context, emailAddress: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$emailAddress")
        }
        context.startActivity(intent)
    }

    private fun dialUser(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }

    private fun messageUser(context: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("sms:$phoneNumber")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        } else {
            // Handle the case where no SMS app is available
            Toast.makeText(context, "No SMS app found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int = users.size
}
