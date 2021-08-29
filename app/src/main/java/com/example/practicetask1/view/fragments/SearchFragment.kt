package com.example.practicetask1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.practicetask1.R
import com.example.practicetask1.view.adapter.SearchFragmentsAdapter
import com.google.android.material.tabs.TabLayout


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout: TabLayout = view.findViewById(R.id.tab_layout)
        val pager2: ViewPager2 = view.findViewById(R.id.pager)

        val adapter = SearchFragmentsAdapter(parentFragmentManager, lifecycle)
        pager2.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText(R.string.by_events))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.by_organizations))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    pager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Empty
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Empty
            }
        })

        pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })
    }
}