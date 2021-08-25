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

class SelectOperationCategoryActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationCategoryBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationCategoryBinding.inflate(layoutInflater)
    }

    private val categoriesViewModel: CategoriesViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getContainer().categoriesViewModel
    }
    private var checkActivity = false
    private val enterSumFlag = "ENTER_SUM_SESSION"
    private val enterTypeFlag = "ENTER_TYPE_OPERATION"
    private val enterCategoryFlag = "SELECT_OPERATION_CATEGORY"
    private val checkedActivityFlag = "CHECKED_ACTIVITY"
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

        checkActivity = intent.getBooleanExtra(checkedActivityFlag, false)
        if (!checkActivity) {
            enterSum = intent.getStringExtra(enterSumFlag)!!
            typeOfOperation = intent.getSerializableExtra(enterTypeFlag)!! as CategoryType
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
            intent.putExtra(enterSumFlag, enterSum)
            intent.putExtra(enterTypeFlag, typeOfOperation)
            intent.putExtra(enterCategoryFlag, nameOfOperation)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            val i = Intent(this, EditOperationActivity::class.java)
            i.putExtra("key3", nameOfOperation)
            setResult(RESULT_OK, i)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
