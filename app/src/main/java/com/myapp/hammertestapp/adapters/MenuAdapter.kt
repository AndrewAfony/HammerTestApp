package com.myapp.hammertestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapp.hammertestapp.databinding.MenuItemBinding
import com.myapp.hammertestapp.domain.model.MenuItem

class MenuAdapter(private val data: List<MenuItem>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val current = data[position]
        holder.binding.apply {
            imageTitle.text = current.title
            imageDescription.text = current.description
        }
    }

    override fun getItemCount(): Int = data.size
}