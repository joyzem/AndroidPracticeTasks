package com.example.practicetask1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetask1.R
import com.example.practicetask1.model.HelpCategory
import com.example.practicetask1.view.adapters.CategoryAdapter

class HelpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryDataset: Array<HelpCategory> = getCategories()
        val adapter = CategoryAdapter(categoryDataset)
        val layoutManager = GridLayoutManager(requireContext(), 2).apply {
        }
        val recyclerView = view.findViewById<RecyclerView>(R.id.category_recycler_view).apply {
            setAdapter(adapter)
            setLayoutManager(layoutManager)
        }
    }

    private fun getCategories(): Array<HelpCategory> {
        return arrayOf(
            HelpCategory(
                resources.getString(R.string.kids),
                ContextCompat.getDrawable(requireContext(), R.drawable.kids)!!
            ),
            HelpCategory(
                resources.getString(R.string.adult),
                ContextCompat.getDrawable(requireContext(), R.drawable.adult)!!
            ),
            HelpCategory(
                resources.getString(R.string.olds),
                ContextCompat.getDrawable(requireContext(), R.drawable.olds)!!
            ),
            HelpCategory(
                resources.getString(R.string.animals),
                ContextCompat.getDrawable(requireContext(), R.drawable.animals)!!
            ),
            HelpCategory(
                resources.getString(R.string.events),
                ContextCompat.getDrawable(requireContext(), R.drawable.events)!!
            )
        )
    }
}
