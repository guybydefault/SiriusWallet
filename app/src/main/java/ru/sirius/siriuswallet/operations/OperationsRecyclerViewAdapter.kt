package ru.sirius.siriuswallet.operations

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.WalletListDateViewBinding
import ru.sirius.siriuswallet.databinding.WalletListOperationViewBinding
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class OperationsRecyclerViewAdapter(val context: Context) :
    RecyclerView.Adapter<AbstractOperationListViewHolder>(),
    IBindingVisitor {

    @RequiresApi(Build.VERSION_CODES.O)
    val dataset: List<Operation> = mutableListOf(
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now(), BigDecimal(-12000),
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now(), BigDecimal(130000),
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(1), BigDecimal(130000),
        ), Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now().minusDays(2), BigDecimal(-12000),
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(2), BigDecimal(130000)
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now().minusDays(4), BigDecimal(130000)
        ),
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now().minusDays(4), BigDecimal(-12000)
        )
    )
        get() {
            return field.sortedByDescending { it.operationDate }
        }

    val viewDataSet = initViewDataset(dataset)

    @SuppressLint("NewApi")
    fun initViewDataset(operations: List<Operation>): MutableList<Any> {
        val viewDataSet = mutableListOf<Any>()
        val it = operations.iterator()
        var next = if (it.hasNext()) it.next() else null
        var prev: Operation? = null
        while (next != null) {
            if (prev == null || !prev.operationDate.toLocalDate()
                    .equals(next.operationDate.toLocalDate())
            ) {
                viewDataSet.add(next.operationDate.toLocalDate())
            }
            viewDataSet.add(next)
            prev = next
            next = if (it.hasNext()) it.next() else null
        }
        return viewDataSet
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractOperationListViewHolder {
        when (viewType) {
            OPERATION_VH_TYPE -> {
                val binding = WalletListOperationViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return OperationViewHolder(binding, parent.context)
            }
            DATE_VH_TYPE -> {
                val binding = WalletListDateViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return OperationsDateViewHolder(binding)
            }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun onBindViewHolder(holder: AbstractOperationListViewHolder, position: Int) {
        holder.bind(this, position)
    }

    override fun getItemCount(): Int {
        return viewDataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewDataSet[position] is Operation) OPERATION_VH_TYPE else DATE_VH_TYPE
    }

    companion object {
        const val DATE_VH_TYPE = 0
        const val OPERATION_VH_TYPE = 1
        val DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM")
    }

    override fun bindOperationViewHolder(
        viewHolder: OperationViewHolder,
        position: Int
    ) {
        val operation = viewDataSet[position] as Operation
        viewHolder.apply {
            operationName.text = operation.operationName
            operationCategory.text = operation.operationCategory
            operationAmount.text =
                "${operation.amount.toPlainString()} ${resources.getString(R.string.rub_symbol)}"
            operationDate.text = operation.operationDate.format(OperationViewHolder.TIME_FORMATTER)
            operationIcon.setImageDrawable(
                ResourcesCompat.getDrawable(
                    resources,
                    operation.categoryResourceId,
                    theme
                )
            )
        }
    }

    @SuppressLint("NewApi")
    override fun bindDateViewHolder(viewHolder: OperationsDateViewHolder, position: Int) {
        val date = viewDataSet[position] as LocalDate
        val dateStr: String
        if (date.isEqual(LocalDate.now())) {
            dateStr = context.getString(R.string.operation_date_today)
        } else if (date.isEqual(LocalDate.now().minusDays(1))) {
            dateStr = context.getString(R.string.operation_date_yesterday)
        } else {
            dateStr = date.format(DATE_FORMATTER)
        }
        viewHolder.date.text = dateStr
    }
}