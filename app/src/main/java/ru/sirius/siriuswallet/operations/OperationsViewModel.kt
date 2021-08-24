package ru.sirius.siriuswallet.operations

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.SiriusWalletContainer
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.model.Operation

@SuppressLint("NewApi")
class OperationsViewModel(container: SiriusWalletContainer) : ViewModel() {
    val operations: MutableLiveData<List<Operation>> = MutableLiveData()

    init {
        viewModelScope.launch {
            operations.postValue(
                mutableListOf<Operation>()
            )
            delay(1000)
            operations.postValue(container.operationsService.placeholderDataset)
            delay(1000)
            val result = container.operationsService.getOperations(103)
            if (result is Response.Success) {
                operations.postValue(result.responseBody)
            } else {
                // TODO
            }
        }
    }
}