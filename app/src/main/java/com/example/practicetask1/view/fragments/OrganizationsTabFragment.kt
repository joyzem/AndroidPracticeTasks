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

class OrganizationsTabFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var queryResultsAdapter: QueryResultsAdapter? = null
    private var keyWordsTextView: TextView? = null
    private var searchResultsTextView: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_organizations_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        keyWordsTextView = view.findViewById(R.id.organizations_key_words)
        searchResultsTextView = view.findViewById(R.id.search_organizations_results)
        recyclerView = view.findViewById(R.id.search_organizations_recycler_view)
        queryResultsAdapter = QueryResultsAdapter(getDataByQuery(""))
        recyclerView?.adapter = queryResultsAdapter
        ResourcesCompat.getDrawable(resources, R.drawable.divider, resources.newTheme())
            ?.let { drawable ->
                recyclerView?.addItemDecoration(
                    ItemDivider(
                        drawable,
                        ItemDivider.VERTICAL,
                        requireContext()
                    )
                )
            }
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        updateQueryResults("")
    }

    fun updateQueryResults(query: String) {
        keyWordsTextView?.text = resources.getString(R.string.key_words, getKeyWords(query))
        searchResultsTextView?.text = resources.getString(
            R.string.search_results,
            getSearchResultsAmount(query),
            resources.getQuantityString(R.plurals.organizations, getSearchResultsAmount(query))
        )
    }

    private fun getSearchResultsAmount(query: String): Int {
        return recyclerView?.adapter?.itemCount ?: 0
    }

    private fun getKeyWords(query: String): String {
        return ""
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
