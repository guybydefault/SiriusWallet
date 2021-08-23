package ru.sirius.siriuswallet

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.model.CategoryItem
import ru.sirius.siriuswallet.model.CategoryType

private var checkedPosition: Int = -1

class CategoryHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.category_fragment, parent, false)) {
    private var icon: ImageView? = null
    private var category: TextView? = null
    private var arrow: ImageView? = null
    private var resources = parent.resources
    private var theme = parent.context.theme


    init {
        icon = itemView.findViewById(R.id.image_category)
        category = itemView.findViewById(R.id.category_text)
        arrow = itemView.findViewById(R.id.arrow_right_change)
    }

    fun bind(categoryItem: CategoryItem, clickListener: OnItemClickListener) {
        icon?.setImageResource(categoryItem.category.categoryResourceId)
        if (categoryItem.category.type == CategoryType.INCOME) {
            icon?.background?.setTint(resources.getColor(R.color.income_category_background_color, theme))
        } else {
            icon?.background?.setTint(resources.getColor(R.color.outcome_category_background_color, theme))
        }
        category?.text = categoryItem.category.name
        arrow!!.visibility = GONE

        itemView.setOnClickListener {
            clickListener.onItemClicked(categoryItem)
            if (arrow!!.visibility == VISIBLE && checkedPosition == adapterPosition) {
                arrow!!.visibility = GONE
                checkedPosition = adapterPosition
            } else {
                arrow!!.visibility = VISIBLE
                checkedPosition = adapterPosition
            }
        }

    }


}