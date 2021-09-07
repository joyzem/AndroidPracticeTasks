package com.example.practicetask1.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicetask1.R
import com.example.practicetask1.model.HelpCategory

class CategoryAdapter(private val dataset: Array<HelpCategory>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val categoryLabel: TextView
        val categoryImage: ImageView

        init {
            categoryLabel = view.findViewById(R.id.category_label)
            categoryImage = view.findViewById(R.id.category_picture)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)

        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(categoryHolder: CategoryViewHolder, position: Int) {
        categoryHolder.categoryLabel.text = dataset[position].title
        categoryHolder.categoryImage.setImageDrawable(dataset[position].image)
    }

    override fun getItemCount() = dataset.size

}