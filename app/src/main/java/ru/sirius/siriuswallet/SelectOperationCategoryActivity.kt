package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationCategoryBinding
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationTypeBinding
import ru.sirius.siriuswallet.model.Category
import ru.sirius.siriuswallet.model.CategoryItem

class SelectOperationCategoryActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationCategoryBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationCategoryBinding.inflate(layoutInflater)
    }

    private val listOfCategory = listOf(
        CategoryItem(R.drawable.ic_icon_bg, "Зарплата", true),
        CategoryItem(R.drawable.ic_icon_bg, "Подработка", false),
        CategoryItem(R.drawable.ic_icon_present, "Подарок", false),
        CategoryItem(R.drawable.ic_icon_cap, "Капитализация", false),
    )

    val categories: MutableList<Category> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.categoryToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.categoryToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.doneButtonBlack.setOnClickListener {
            goToSelectOperationActivity()
        }

        binding.operationListRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CategoryAdapter(listOfCategory)

        }

    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


    fun goToSelectOperationActivity() {
        val intent = Intent(this, EditOperationActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


}