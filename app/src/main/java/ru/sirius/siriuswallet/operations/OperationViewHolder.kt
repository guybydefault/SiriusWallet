package ru.sirius.siriuswallet.operations

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.WalletListOperationViewBinding
import ru.sirius.siriuswallet.model.Operation
import java.time.format.DateTimeFormatter

class OperationViewHolder(
    binding: WalletListOperationViewBinding,
    val parentContext: Context
) : AbstractOperationListViewHolder(binding) {

    val operationIcon: ImageView
    val operationName: TextView
    val operationCategory: TextView
    val operationDate: TextView
    val operationAmount: TextView

    val resources: Resources
        get() {
            return parentContext.resources
        }

    val theme: Resources.Theme
        get() {
            return parentContext.theme
        }

    init {
        operationIcon = binding.operationIcon
        operationName = binding.operationName
        operationCategory = binding.operationCategory
        operationDate = binding.operationDate
        operationAmount = binding.operationSum
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        var TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
    }

    override fun bind(bindingVisitor: IBindingVisitor, position: Int) {
        bindingVisitor.bindOperationViewHolder(this, position)
    }
}