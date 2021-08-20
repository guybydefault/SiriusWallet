package ru.sirius.siriuswallet.operations

import android.widget.TextView
import ru.sirius.siriuswallet.databinding.WalletListDateViewBinding

class OperationsDateViewHolder(binding: WalletListDateViewBinding) :
    AbstractOperationListViewHolder(binding) {
    val date: TextView = binding.date
    override fun bind(bindingVisitor: IBindingVisitor, position: Int) {
        bindingVisitor.bindDateViewHolder(this, position)
    }
}