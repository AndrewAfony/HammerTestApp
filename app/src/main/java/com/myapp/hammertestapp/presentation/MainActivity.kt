package com.myapp.hammertestapp.presentation

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.myapp.hammertestapp.adapters.BannerAdapter
import com.myapp.hammertestapp.adapters.MenuAdapter
import com.myapp.hammertestapp.databinding.ActivityMainBinding
import com.myapp.hammertestapp.domain.model.Banner
import com.myapp.hammertestapp.domain.model.Drink
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var menuAdapter: MenuAdapter

    private val viewModel: FoodViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBannerRecyclerView()

        viewModel.food.observe(this) {
            setupMenuRecyclerView(it)
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenCreated {
            viewModel.eventFlow.collectLatest { event ->
                when(event) {
                    is FoodViewModel.UIEvent.ShowSnackbar -> {
                        Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG)
                            .setAnchorView(binding.bottomNavigationView)
                            .show()
                    }
                }
            }
        }

        viewModel.isLoading.observe(this) {
            when(it) {
                true -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                false -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setupBannerRecyclerView() {
        bannerAdapter = BannerAdapter(listOf(Banner(), Banner()))
        binding.rvBanner.apply {
            adapter = bannerAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupMenuRecyclerView(data: List<Drink>) {
        val divider = DividerItemDecoration(this@MainActivity, RecyclerView.VERTICAL)
        menuAdapter = MenuAdapter(data)
        binding.rvMenu.apply {
            adapter = menuAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(divider)
        }
    }
}