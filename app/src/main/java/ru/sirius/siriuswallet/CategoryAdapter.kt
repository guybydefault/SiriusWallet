package ru.sirius.siriuswallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.model.CategoryItem

class CategoryAdapter(private val list: List<CategoryItem>) :
    RecyclerView.Adapter<CategoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val categoryItem: CategoryItem = list[position]
        holder.bind(categoryItem)


    }

    override fun getItemCount(): Int = list.size


}