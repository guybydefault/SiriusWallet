package ru.sirius.siriuswallet.operations

interface IBindingVisitor {

    fun bindOperationViewHolder(viewHolder: OperationViewHolder, position: Int)

    fun bindDateViewHolder(viewHolder: OperationsDateViewHolder, position: Int)

}