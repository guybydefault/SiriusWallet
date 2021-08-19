package ru.sirius.siriuswallet

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.databinding.WalletListOperationViewBinding
import ru.sirius.siriuswallet.model.Operation
import java.time.format.DateTimeFormatter

class OperationViewHolder(
    binding: WalletListOperationViewBinding,
    val parentContext: Context
) : RecyclerView.ViewHolder(binding.root) {

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

    @RequiresApi(Build.VERSION_CODES.O)
    fun onBind(operation: Operation) {
        operationName.text = operation.operationName
        operationCategory.text = operation.operationCategory
        operationAmount.text =
            "${operation.amount.toPlainString()} ${resources.getString(R.string.rub_symbol)}"
        operationDate.text = operation.operationDate.format(TIME_FORMATTER)
        operationIcon.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                operation.categoryResourceId,
                theme
            )
        )
    }

    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        var TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
    }
}