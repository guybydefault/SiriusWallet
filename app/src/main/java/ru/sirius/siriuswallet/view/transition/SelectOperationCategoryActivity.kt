package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ru.sirius.siriuswallet.*
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationCategoryBinding
import ru.sirius.siriuswallet.model.CategoryItem
import ru.sirius.siriuswallet.model.CategoryType
import ru.sirius.siriuswallet.view.transition.ActivityConstants.CHECKED_ACTIVITY_FLAG
import ru.sirius.siriuswallet.view.transition.ActivityConstants.ENTER_SUM_SESSION_FLAG
import ru.sirius.siriuswallet.view.transition.ActivityConstants.ENTER_TYPE_OPERATION_FLAG
import ru.sirius.siriuswallet.view.transition.ActivityConstants.RESULT_OPERATION_COMPONENT_FLAG
import ru.sirius.siriuswallet.view.transition.ActivityConstants.SELECT_OPERATION_CATEGORY_FLAG

class SelectOperationCategoryActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationCategoryBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationCategoryBinding.inflate(layoutInflater)
    }

    private val selectOperationCategoryViewModel: SelectOperationCategoryViewModel by lazy(LazyThreadSafetyMode.NONE) {
        getContainer().createSelectOperationCategoryViewModel()
    }

    private var checkActivity = false
    private var enterSum = ""
    private var typeOfOperation: CategoryType? = null
    private var selectedCategoryId: Int = 0

    private val recyclerViewAdapter = CategoryAdapter(mutableListOf(), object : OnCategoryClickListener {
        override fun onCategoryClicked(categoryItem: CategoryItem) {
            binding.doneButtonBlack.isEnabled = true
            binding.doneButtonBlack.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            selectedCategoryId = categoryItem.category.id
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initInputParameters()
        setupViewModel()
        setupErrorToasts()
        setupNavigation()
        setupRecyclerView()
        setupProgressBar()
        setupDoneButton()
    }

    private fun initInputParameters() {
        checkActivity = intent.getBooleanExtra(CHECKED_ACTIVITY_FLAG, false)
        if (!checkActivity) {
            enterSum = intent.getStringExtra(ENTER_SUM_SESSION_FLAG)!!
        }
        typeOfOperation = intent.getSerializableExtra(ENTER_TYPE_OPERATION_FLAG)!! as CategoryType
    }

    private fun setupViewModel() {
        selectOperationCategoryViewModel.categoryType = typeOfOperation
    }

    private fun setupProgressBar() {
        selectOperationCategoryViewModel.operationsLoadingInProgress.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.INVISIBLE
            }
        }
    }

    private fun setupNavigation() {
        binding.categoryToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.categoryToolbar.setNavigationOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }
    }

    private fun setupRecyclerView() {
        binding.operationListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = recyclerViewAdapter
        }
        selectOperationCategoryViewModel.categories.observe(this) { it ->
            recyclerViewAdapter.updateList(it.map { CategoryItem(it, false) })
        }
    }

    private fun setupErrorToasts() {
        selectOperationCategoryViewModel.err.observe(this) {
            showToast(this, R.drawable.ic_error, it, Toast.LENGTH_LONG)
        }
    }

    private fun setupDoneButton() {
        binding.doneButtonBlack.setOnClickListener {
            goToEditOperationActivity()
        }
    }

    private fun goToEditOperationActivity() {
        if (!checkActivity) {
            val intent = Intent(this, EditOperationActivity::class.java)
            intent.putExtra(ENTER_SUM_SESSION_FLAG, enterSum)
            intent.putExtra(ENTER_TYPE_OPERATION_FLAG, typeOfOperation)
            intent.putExtra(SELECT_OPERATION_CATEGORY_FLAG, selectedCategoryId)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            val i = Intent(this, EditOperationActivity::class.java)
            i.putExtra(RESULT_OPERATION_COMPONENT_FLAG, selectedCategoryId)
            setResult(RESULT_OK, i)
            finish()
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
