package ru.sirius.siriuswallet.view.transition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.data.CategoryService
import ru.sirius.siriuswallet.data.OperationService
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.Operation
import java.math.BigDecimal
import java.time.LocalDateTime

class EditOperationViewModel(val operationService: OperationService, val categoriesService: CategoryService) : ViewModel() {
    var selectedCategoryId: Int = 0
        set(value) {
            field = value
            updateCategory(field)
        }
    var amount: BigDecimal = BigDecimal.ZERO
    var dateTime: LocalDateTime = LocalDateTime.now()

    val successfullyCreatedOperation = MutableLiveData<Operation>()
    val err = MutableLiveData<String>()

    var category: MutableLiveData<Category> = MutableLiveData()
    private fun updateCategory(id: Int) {
        viewModelScope.launch {
            category.postValue(categoriesService.getCachedCategoryById(selectedCategoryId))
        }
    }

    fun createOperation() {
        viewModelScope.launch {
            val response = operationService.createOperation(
                Operation(0, dateTime, amount, category.value!!)
            )
            if (response is Response.Success) {
                successfullyCreatedOperation.postValue(response.responseBody)
            } else {
                err.postValue((response as Response.Error).errorMessage)
            }
        }
    }
}