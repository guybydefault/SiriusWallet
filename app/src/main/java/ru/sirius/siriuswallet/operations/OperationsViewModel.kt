package ru.sirius.siriuswallet.operations

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.SiriusWalletContainer
import ru.sirius.siriuswallet.dao.Response
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

@SuppressLint("NewApi")
class OperationsViewModel(container: SiriusWalletContainer) : ViewModel() {
    val operations: MutableLiveData<List<Operation>> = MutableLiveData()

    init {
        operations.value = mutableListOf(
            Operation(
                "Супермаркеты", "Траты", R.drawable.ic_supermarket,
                LocalDateTime.now(), BigDecimal(-12000),
            )
        )
        viewModelScope.launch {
            val result = container.operationsService.getOperations(102)
            if (result is Response.Success) {
                operations.value = result.responseBody
            } else {
                TODO()
            }
        }
    }
}