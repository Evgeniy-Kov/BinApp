package com.example.binapp.ui.root

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.binapp.R
import com.example.binapp.databinding.ActivityRootBinding
import com.google.android.material.tabs.TabLayoutMediator

class RootActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRootBinding.inflate(layoutInflater)
    }

    private lateinit var tabLayoutMediator: TabLayoutMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.viewPager.adapter = ViewPagerAdapter(
            supportFragmentManager,
            lifecycle
        )

        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.search_string)
                    1 -> tab.text = getString(R.string.history_string)
                }
            }
        tabLayoutMediator.attach()
    }
}