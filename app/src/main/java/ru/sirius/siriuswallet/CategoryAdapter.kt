package ru.sirius.siriuswallet

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.CategoryFragmentBinding
import ru.sirius.siriuswallet.model.CategoryItem
import ru.sirius.siriuswallet.model.CategoryType

internal class CategoryAdapter(
    private var list: List<CategoryItem>,
    private val categoryClickListener: OnCategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val viewBinding = CategoryFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = CategoryViewHolder(viewBinding, parent.context)

        viewBinding.root.setOnClickListener {
            val adapterPosition = viewHolder.adapterPosition

            selectedItemPos = adapterPosition
            if (lastItemSelectedPos == -1)
                lastItemSelectedPos = selectedItemPos
            else {
                notifyItemChanged(lastItemSelectedPos)
                lastItemSelectedPos = selectedItemPos
            }
            notifyItemChanged(selectedItemPos)
            categoryClickListener.onCategoryClicked(list[adapterPosition])
        }
        return viewHolder
    }

    fun updateList(list: List<CategoryItem>) {
        this.list = list
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryItem = list[position]
        holder.apply {
            if (categoryItem.category.type == CategoryType.INCOME) {
                categoryIcon.background?.setTint(context.resources.getColor(R.color.income_category_background_color, context.theme))
            } else {
                categoryIcon.background?.setTint(context.resources.getColor(R.color.outcome_category_background_color, context.theme))
            }
            categoryIcon.setImageResource(categoryItem.category.categoryResourceId)
            categoryText.text = categoryItem.category.name
            arrowIcon.isVisible = position == selectedItemPos
        }
    }

    override fun getItemCount(): Int = list.size

    inner class CategoryViewHolder(viewBinding: CategoryFragmentBinding, val context: Context) : RecyclerView.ViewHolder(viewBinding.root) {
        val categoryIcon: ImageView = viewBinding.imageCategory
        val categoryText: TextView = viewBinding.categoryText
        val arrowIcon: ImageView = viewBinding.arrowRightChange
    }
}

interface OnCategoryClickListener {
    fun onCategoryClicked(categoryItem: CategoryItem)
}