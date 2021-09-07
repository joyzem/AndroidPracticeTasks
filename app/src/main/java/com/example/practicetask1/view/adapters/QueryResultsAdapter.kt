package com.example.practicetask1.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetask1.R
import com.example.practicetask1.model.QueryResult

class QueryResultsAdapter(private var dataset: List<QueryResult>?) :
    RecyclerView.Adapter<QueryResultsAdapter.QueryResultsViewHolder>() {

    class QueryResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val queryResultText: TextView

        init {
            queryResultText = itemView.findViewById(R.id.query_result_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueryResultsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_item_layout, parent, false)
        return QueryResultsViewHolder(view)
    }

    override fun onBindViewHolder(holder: QueryResultsViewHolder, position: Int) {
        holder.queryResultText.text = dataset?.get(position)?.title
    }

    override fun getItemCount() = dataset?.size ?: 0

    fun updateDataset(newDataset: List<QueryResult>) {
        dataset = newDataset
    }
}
