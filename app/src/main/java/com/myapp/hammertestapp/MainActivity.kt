package com.myapp.hammertestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.myapp.hammertestapp.adapters.BannerAdapter
import com.myapp.hammertestapp.databinding.ActivityMainBinding
import com.myapp.hammertestapp.domain.model.Banner

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bannerAdapter: BannerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBannerRecyclerView()
    }

    private fun setupBannerRecyclerView() {
        bannerAdapter = BannerAdapter(listOf(Banner(), Banner()))
        binding.rvBanner.apply {
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }
}