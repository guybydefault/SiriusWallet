package ru.sirius.siriuswallet.operations

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.WalletListOperationViewBinding
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

class OperationsRecyclerViewAdapter : RecyclerView.Adapter<OperationViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.O)
    val dataset: List<Operation> = mutableListOf(
        Operation(
            "Супермаркеты", "Траты", R.drawable.ic_supermarket,
            LocalDateTime.now(), BigDecimal(-12000),
        ),
        Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now(), BigDecimal(130000)
        ), Operation(
            "Зарплата", "Пополнение", R.drawable.ic_salary,
            LocalDateTime.now(), BigDecimal(130000)
        )
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationViewHolder {
        val binding = WalletListOperationViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OperationViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: OperationViewHolder, position: Int) {
        holder.onBind(dataset[position])
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}