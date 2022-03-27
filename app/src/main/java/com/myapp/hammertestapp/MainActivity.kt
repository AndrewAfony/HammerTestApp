package com.myapp.hammertestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.hammertestapp.adapters.BannerAdapter
import com.myapp.hammertestapp.adapters.MenuAdapter
import com.myapp.hammertestapp.databinding.ActivityMainBinding
import com.myapp.hammertestapp.domain.model.Banner
import com.myapp.hammertestapp.domain.model.MenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBannerRecyclerView()
        setupMenuRecyclerView()
    }

    private fun setupBannerRecyclerView() {
        bannerAdapter = BannerAdapter(listOf(Banner(), Banner()))
        binding.rvBanner.apply {
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupMenuRecyclerView() {
        val divider = DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
        menuAdapter = MenuAdapter(listOf(MenuItem("Пеперони", "Описание", ""), MenuItem("Пеперони 2", "Описание 2", "")))
        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(divider)
        }
    }
}