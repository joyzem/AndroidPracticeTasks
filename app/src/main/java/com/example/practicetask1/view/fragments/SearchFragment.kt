package com.example.practicetask1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.practicetask1.R
import com.example.practicetask1.view.adapters.SearchFragmentsAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout

class SearchFragment : Fragment() {

    private var searchToolbarInactive: MaterialToolbar? = null
    private var searchLineActive: FrameLayout? = null
    private var queryEditText: EditText? = null
    private lateinit var tabLayout: TabLayout
    private lateinit var pager2: ViewPager2
    private lateinit var adapter: SearchFragmentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchToolbarInactive = view.findViewById(R.id.search_toolbar)
        searchLineActive = view.findViewById(R.id.search_active)
        queryEditText = view.findViewById(R.id.edit_search_text)

        queryEditText?.addTextChangedListener {
        }
        setTabLayout(view)
        view.findViewById<MaterialToolbar>(R.id.search_toolbar).menu.getItem(0)
            .setOnMenuItemClickListener {
                swapToolbarToSearch()
                true
            }
    }

    private fun setTabLayout(view: View) {
        tabLayout = view.findViewById(R.id.tab_layout)
        pager2 = view.findViewById(R.id.pager)
        adapter = SearchFragmentsAdapter(parentFragmentManager, lifecycle)
        pager2.adapter = adapter

        tabLayout.addTab(tabLayout.newTab().setText(R.string.by_events))
        tabLayout.addTab(tabLayout.newTab().setText(R.string.by_organizations))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    pager2.currentItem = tab.position
                    swapToolbarToDefault()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Nothing
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Nothing
            }
        })

        pager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                tabLayout.selectTab(tabLayout.getTabAt(position))
                queryEditText?.setHint(
                    if (position == 0)
                        R.string.enter_event_name else R.string.enter_organization_name
                )
            }
        })
    }

    private fun swapToolbarToSearch() {
        with(requireView()) {
            searchToolbarInactive?.visibility = View.GONE
            searchLineActive?.visibility = View.VISIBLE
        }
    }

    private fun swapToolbarToDefault() {
        with(requireView()) {
            searchToolbarInactive?.visibility = View.VISIBLE
            searchLineActive?.visibility = View.GONE
        }
    }
}
