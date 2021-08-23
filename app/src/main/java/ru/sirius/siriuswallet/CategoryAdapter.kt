package ru.sirius.siriuswallet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.model.CategoryItem

class CategoryAdapter(
    private var list: List<CategoryItem>,
    val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<CategoryHolder>() {

    fun updateList(list: List<CategoryItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val categoryItem: CategoryItem = list[position]
        holder.bind(categoryItem, itemClickListener)
    }

    override fun getItemCount(): Int = list.size


}

interface OnItemClickListener {
    fun onItemClicked(categoryItem: CategoryItem)
}