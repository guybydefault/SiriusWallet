package ru.sirius.siriuswallet.operations

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.ApplicationConstants
import ru.sirius.siriuswallet.data.OperationService
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.model.Operation
import ru.sirius.siriuswallet.model.income
import ru.sirius.siriuswallet.model.outcome
import java.math.BigDecimal

@SuppressLint("NewApi")
class OperationsViewModel(val operationService: OperationService) : ViewModel() {
    val operations: MutableLiveData<List<Operation>> = MutableLiveData()
    val income = MutableLiveData<BigDecimal>()
    val outcome = MutableLiveData<BigDecimal>()
    val totalBalance = MutableLiveData<BigDecimal>()
    val err = MutableLiveData<String>()
    val operationsLoadingInProgress = MutableLiveData<Boolean>()

    init {
    }

    fun reloadViewModel() {
        viewModelScope.launch {
            operations.postValue(
                mutableListOf<Operation>()
            )
            if (ApplicationConstants.TEST_DELAY > 0) {
                delay(ApplicationConstants.TEST_DELAY)
            }
            operationsLoadingInProgress.postValue(true)
            operationService.loadOperations(ApplicationConstants.WALLET_ID, onCacheLoaded = {
                onLoad(it)
            }, onNetworkLoaded = {
                operationsLoadingInProgress.postValue(false)
                onLoad(it)
            })
        }
    }

    private fun onLoad(response: Response<List<Operation>>) {
        if (response is Response.Success) {
            operations.postValue(response.responseBody)
            income.postValue(response.responseBody.income())
            outcome.postValue(response.responseBody.outcome())
            totalBalance.postValue(response.responseBody.income().minus(response.responseBody.outcome()))
        } else {
            err.postValue((response as Response.Error).errorMessage)
        }
    }
}