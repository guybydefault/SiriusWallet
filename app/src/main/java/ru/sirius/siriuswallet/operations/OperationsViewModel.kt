package ru.sirius.siriuswallet.operations

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.sirius.siriuswallet.SiriusWalletContainer
import ru.sirius.siriuswallet.model.Operation

@SuppressLint("NewApi")
class OperationsViewModel(container: SiriusWalletContainer) : ViewModel() {
    val operations: MutableLiveData<List<Operation>> = MutableLiveData()

    init {
        operations.value = container.dataRepository.dataset
    }
}