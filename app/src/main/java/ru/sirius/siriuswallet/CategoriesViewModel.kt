package ru.sirius.siriuswallet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.sirius.siriuswallet.dao.Response
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryType

class CategoriesViewModel(container: SiriusWalletContainer) : ViewModel() {
    val categories = MutableLiveData<List<Category>>()
    private val categoriesService = container.categoriesService

    init {
        viewModelScope.launch {
            //TODO get category type
            val resp = categoriesService.getCategories(CategoryType.OUTCOME)
            if (resp is Response.Success) {
                categories.postValue(resp.responseBody)
            } else {
                // TODO
            }
        }
    }
}