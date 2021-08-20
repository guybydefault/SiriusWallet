package ru.sirius.siriuswallet.operations

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class AbstractOperationListViewHolder(binding: ViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(bindingVisitor: IBindingVisitor, position: Int)

}