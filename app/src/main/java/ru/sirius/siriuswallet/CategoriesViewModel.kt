package ru.sirius.siriuswallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.data.Response
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoriesViewModel(container: SiriusWalletContainer) : ViewModel() {
    var categoryType: CategoryType? = null
        set(value) {
            field = value
            updateCategories(value!!)
        }

    val categories = MutableLiveData<List<Category>>()
    private val categoriesService = container.categoriesService

    private fun updateCategories(categoryType: CategoryType) {
        viewModelScope.launch {
            //TODO get category type
            val resp = categoriesService.getCategories(categoryType)
            if (resp is Response.Success) {
                categories.postValue(resp.responseBody)
            } else {
                // TODO
            }
        }
    }
}