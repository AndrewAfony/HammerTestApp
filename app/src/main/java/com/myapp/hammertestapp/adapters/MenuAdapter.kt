package com.myapp.hammertestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myapp.hammertestapp.R
import com.myapp.hammertestapp.databinding.MenuItemBinding
import com.myapp.hammertestapp.domain.model.Drink
import com.myapp.hammertestapp.domain.model.MenuItem

class MenuAdapter(private val data: List<Drink>): RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(val binding: MenuItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return MenuViewHolder(
            MenuItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val current = data[position]
        holder.binding.apply {
            imageTitle.text = current.strDrink
            imageDescription.text = current.strInstructions
            Glide.with(holder.binding.root)
                .load(current.strDrinkThumb)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_error)
                .into(holder.binding.foodImage)
        }
    }

    override fun getItemCount(): Int = data.size
}