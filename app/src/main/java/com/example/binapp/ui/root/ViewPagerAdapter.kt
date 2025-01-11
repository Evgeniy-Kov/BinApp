package com.example.binapp.ui.root

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.binapp.ui.history.HistoryFragment
import com.example.binapp.ui.search.SearchFragment

class ViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return ITEM_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SearchFragment.newInstance()
            else -> HistoryFragment.newInstance()
        }
    }

    private companion object {
        const val ITEM_COUNT = 2
    }
}