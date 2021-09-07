package com.example.practicetask1.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetask1.R
import com.example.practicetask1.model.QueryResult
import com.example.practicetask1.view.adapters.ItemDivider
import com.example.practicetask1.view.adapters.QueryResultsAdapter

class EventsTabFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var queryResultsAdapter: QueryResultsAdapter? = null
    private var dataset: List<QueryResult> = listOf()
    private var keyWordsTextView: TextView? = null
    private var searchResultsTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_events_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        keyWordsTextView = view.findViewById(R.id.events_key_words)
        searchResultsTextView = view.findViewById(R.id.search_events_results)
        recyclerView = view.findViewById(R.id.search_events_recycler_view)
        queryResultsAdapter = QueryResultsAdapter(listOf())
        recyclerView?.adapter = queryResultsAdapter
        ResourcesCompat.getDrawable(resources, R.drawable.divider, resources.newTheme())
            ?.let { drawable ->
                recyclerView?.addItemDecoration(ItemDivider(drawable, ItemDivider.VERTICAL, requireContext()))
            }
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        updateQueryResults("")
    }

    fun updateQueryResults(query: String) {
        dataset = getDataByQuery(query)
        keyWordsTextView?.text =
            "${resources.getString(R.string.key_words)} ${getKeyWordsAmount(query)}"
        searchResultsTextView?.text =
            "${resources.getString(R.string.search_results)} ${getSearchResults(query)}"
        updateRecyclerView(dataset)
    }

    private fun getSearchResults(query: String): Int {
        return 0
    }

    private fun getKeyWordsAmount(query: String): Int {
        return 0
    }

    private fun updateRecyclerView(newDataset: List<QueryResult>) {
        queryResultsAdapter?.updateDataset(newDataset)
        recyclerView?.adapter?.notifyDataSetChanged()
    }

    private fun getDataByQuery(query: String): List<QueryResult> {
        return resources.getStringArray(R.array.organizations_array).toList().map { title ->
            QueryResult(title)
        }.shuffled()
    }

}