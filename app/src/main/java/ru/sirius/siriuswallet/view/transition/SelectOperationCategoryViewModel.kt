package ru.sirius.siriuswallet.view.transition

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.SiriusWalletContainer
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class SelectOperationCategoryViewModel(container: SiriusWalletContainer) : ViewModel() {
    var categoryType: CategoryType? = null
        set(value) {
            field = value
            if (field != null) {
                updateCategories(value!!)
            }
        }

    val operationsLoadingInProgress = MutableLiveData<Boolean>()

    val err = MutableLiveData<String>()

    val categories = MutableLiveData<List<Category>>()
    private val categoriesService = container.categoriesService

    private fun updateCategories(categoryType: CategoryType) {
        viewModelScope.launch {
            operationsLoadingInProgress.postValue(true)
            categoriesService.getCategories(categoryType) { response, lastStage ->
                if (response is Response.Success) {
                    categories.postValue(response.responseBody)
                } else {
                    err.postValue((response as Response.Error).errorMessage)
                }
                if (lastStage) {
                    operationsLoadingInProgress.postValue(false)
                }
            }
        }
    }
}