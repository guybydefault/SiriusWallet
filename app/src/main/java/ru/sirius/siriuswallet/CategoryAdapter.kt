package ru.sirius.siriuswallet.presentation.transaction

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.CategoryFragmentBinding
import ru.sirius.siriuswallet.model.CategoryItem

internal class CategoryAdapter(
    private val list: List<CategoryItem>,
    private val categoryClickListener: OnCategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.OperationViewHolder>() {

    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val viewBinding = CategoryFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val viewHolder = OperationViewHolder(viewBinding)

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

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        val categoryItem = list[position]
        holder.apply {
            categoryIcon.setImageResource(categoryItem.categoryImage)
            //operationIcon.background.setTint(resources.getColor(R.color.income_category_background_color, theme))
            categoryText.text = categoryItem.description
            arrowIcon.isVisible = position == selectedItemPos
        }
    }

    override fun getItemCount(): Int = list.size

    inner class OperationViewHolder(private val viewBinding: CategoryFragmentBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        val categoryIcon: ImageView = viewBinding.imageCategory
        val categoryText: TextView = viewBinding.categoryText
        val arrowIcon: ImageView = viewBinding.arrowRightChange
    }
}

interface OnCategoryClickListener {
    fun onCategoryClicked(categoryItem: CategoryItem)
}
