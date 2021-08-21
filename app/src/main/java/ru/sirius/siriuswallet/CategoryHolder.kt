package ru.sirius.siriuswallet

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.model.CategoryItem


class CategoryHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.category_fragment, parent, false)) {
    private var icon: ImageView? = null
    private var category: TextView? = null
    private var arrow: ImageView? = null

    init {
        icon = itemView.findViewById(R.id.image_category)
        category = itemView.findViewById(R.id.category_text)
        arrow = itemView.findViewById(R.id.arrow_right_change)
    }

    fun bind(categoryItem: CategoryItem) {
        icon?.setImageResource(categoryItem.icon)
        category?.text = categoryItem.category
        if (!categoryItem.arrow) {
            arrow!!.visibility = GONE
        }
    }



}