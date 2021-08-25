package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.sirius.siriuswallet.*
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationCategoryBinding
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryItem
import ru.sirius.siriuswallet.model.CategoryType
import ru.sirius.siriuswallet.model.Constants.CHECKED_ACTIVITY_FLAG
import ru.sirius.siriuswallet.model.Constants.ENTER_SUM_SESSION_FLAG
import ru.sirius.siriuswallet.model.Constants.ENTER_TYPE_OPERATION_FLAG
import ru.sirius.siriuswallet.model.Constants.RESULT_OPERATION_COMPONENT_FLAG
import ru.sirius.siriuswallet.model.Constants.SELECT_OPERATION_CATEGORY_FLAG

class SelectOperationCategoryActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationCategoryBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationCategoryBinding.inflate(layoutInflater)
    }

    private val categoriesViewModel: CategoriesViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getContainer().categoriesViewModel
    }
    private var checkActivity = false
    private var enterSum = ""
    private lateinit var typeOfOperation: CategoryType
    private var nameOfOperation = ""

    private val listOfCategoryPlaceholder = listOf(
        CategoryItem(Category(0, 0, "Зарплата", CategoryType.INCOME, R.drawable.ic_salary), false),
        CategoryItem(Category(0, 0, "Подработка", CategoryType.INCOME, R.drawable.ic_salary), false),
        CategoryItem(Category(0, 0, "Перевод", CategoryType.INCOME, R.drawable.ic_salary), false),
        CategoryItem(Category(0, 0, "Подарок", CategoryType.INCOME, R.drawable.ic_gift), false)
    )

    private val recyclerViewAdapter = CategoryAdapter(listOfCategoryPlaceholder, object : OnCategoryClickListener {
        override fun onCategoryClicked(categoryItem: CategoryItem) {
            binding.doneButtonBlack.isEnabled = true
            binding.doneButtonBlack.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            nameOfOperation = categoryItem.category.name
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        checkActivity = intent.getBooleanExtra(CHECKED_ACTIVITY_FLAG, false)
        if (!checkActivity) {
            enterSum = intent.getStringExtra(ENTER_SUM_SESSION_FLAG)!!
            typeOfOperation = intent.getSerializableExtra(ENTER_TYPE_OPERATION_FLAG)!! as CategoryType
            categoriesViewModel.categoryType = typeOfOperation
        }

        binding.categoryToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.categoryToolbar.setNavigationOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        binding.doneButtonBlack.setOnClickListener {
            goToEditOperationActivity()
        }

        binding.operationListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }

        categoriesViewModel.categories.observe(this) { it ->
            recyclerViewAdapter.updateList(it.map { CategoryItem(it, false) })
        }
    }

    private fun goToEditOperationActivity() {
        if (!checkActivity) {
            val intent = Intent(this, EditOperationActivity::class.java)
            intent.putExtra(ENTER_SUM_SESSION_FLAG, enterSum)
            intent.putExtra(ENTER_TYPE_OPERATION_FLAG, typeOfOperation)
            intent.putExtra(SELECT_OPERATION_CATEGORY_FLAG, nameOfOperation)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            val i = Intent(this, EditOperationActivity::class.java)
            i.putExtra(RESULT_OPERATION_COMPONENT_FLAG, nameOfOperation)
            setResult(RESULT_OK, i)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
