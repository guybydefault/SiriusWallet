package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager

import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationCategoryBinding
import ru.sirius.siriuswallet.dao.network.dto.CategoryDto
import ru.sirius.siriuswallet.model.CategoryItem
import ru.sirius.siriuswallet.presentation.transaction.CategoryAdapter
import ru.sirius.siriuswallet.presentation.transaction.OnCategoryClickListener

class SelectOperationCategoryActivity : AppCompatActivity(), OnCategoryClickListener {
    private val binding: ActivitySelectOperationCategoryBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationCategoryBinding.inflate(layoutInflater)
    }

    private val listOfCategory = listOf(
        CategoryItem(R.drawable.ic_salary, "Зарплата"),
        CategoryItem(R.drawable.ic_salary, "Подработка"),
        CategoryItem(R.drawable.ic_gift, "Подарок"),
        CategoryItem(R.drawable.ic_capitalization, "Капитализация")
    )

    val categories: MutableList<CategoryDto> = arrayListOf()
    var enterOperationSum = ""
    var typeOfOperation = ""
    var nameOfOperation = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        enterOperationSum = intent.getStringExtra("ENTER_SUM_SESSION")!!
        typeOfOperation = intent.getStringExtra("OPERATION_CATEGORY")!!

        binding.categoryToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.categoryToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.doneButtonBlack.setOnClickListener {
            goToEditOperationActivity()
        }

        binding.operationListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryAdapter(listOfCategory, this@SelectOperationCategoryActivity)
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    fun goToEditOperationActivity() {
        val intent = Intent(this, EditOperationActivity::class.java).apply {
            putExtra("ENTER_SUM_SESSION", enterOperationSum)
            putExtra("OPERATION_CATEGORY", typeOfOperation)
            putExtra("SELECT_OPERATION_CATEGORY", nameOfOperation)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun onCategoryClicked(categoryItem: CategoryItem) {
        nameOfOperation = categoryItem.description
    }
}