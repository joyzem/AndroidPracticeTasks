package com.example.practicetask1.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.practicetask1.view.fragments.EventsTabFragment
import com.example.practicetask1.view.fragments.OrganizationsTabFragment

class SearchFragmentsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return EventsTabFragment()
            else -> return OrganizationsTabFragment()
        }
    }
}
