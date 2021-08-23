package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationCategoryBinding
import ru.sirius.siriuswallet.dao.network.dto.CategoryDto
import ru.sirius.siriuswallet.model.CategoryItem

class SelectOperationCategoryActivity : AppCompatActivity(), OnItemClickListener {
    private val binding: ActivitySelectOperationCategoryBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationCategoryBinding.inflate(layoutInflater)
    }

    private val listOfCategory = listOf(
        CategoryItem(R.drawable.ic_icon_bg, "Зарплата", false),
        CategoryItem(R.drawable.ic_icon_bg, "Подработка", false),
        CategoryItem(R.drawable.ic_icon_present, "Подарок", false),
        CategoryItem(R.drawable.ic_icon_cap, "Капитализация", false),
    )

    val categories: MutableList<CategoryDto> = arrayListOf()



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
            adapter = CategoryAdapter(listOfCategory, this@SelectOperationCategoryActivity)
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

    override fun onItemClicked(categoryItem: CategoryItem) {
        Toast.makeText(this, categoryItem.category, Toast.LENGTH_LONG).show()


    }


}