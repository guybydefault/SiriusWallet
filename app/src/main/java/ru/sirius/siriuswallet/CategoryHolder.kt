package ru.sirius.siriuswallet

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.model.CategoryItem

private var checkedPosition: Int = -1

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

    fun bind(categoryItem: CategoryItem, clickListener: OnItemClickListener) {
        icon?.setImageResource(categoryItem.icon)
        category?.text = categoryItem.category
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