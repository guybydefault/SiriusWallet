package ru.sirius.siriuswallet.operations

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.WalletAndUserConstants
import ru.sirius.siriuswallet.data.OperationService
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.model.Operation

@SuppressLint("NewApi")
class OperationsViewModel(val operationService: OperationService) : ViewModel() {
    val operations: MutableLiveData<List<Operation>> = MutableLiveData()
    val err = MutableLiveData<String>()

    fun init() {
        viewModelScope.launch {
            operations.postValue(
                mutableListOf<Operation>()
            )
            delay(1000) // TODO remove
            operationService.loadOperations(WalletAndUserConstants.WALLET_ID) { response ->
                if (response is Response.Success) {
                    operations.postValue(response.responseBody)
                } else {
                    err.postValue((response as Response.Error).errorMessage)
                }
            }
        }
    }
}