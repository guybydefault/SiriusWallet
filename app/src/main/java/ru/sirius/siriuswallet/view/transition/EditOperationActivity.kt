package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.datepicker.MaterialDatePicker
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.ActivityEditOperationBinding
import ru.sirius.siriuswallet.model.CategoryType
import java.util.*

class EditOperationActivity : AppCompatActivity() {
    private val binding: ActivityEditOperationBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEditOperationBinding.inflate(layoutInflater)
    }
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var launcherType: ActivityResultLauncher<Intent>? = null
    private var launcherCategory: ActivityResultLauncher<Intent>? = null

    val months =
        arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа",
            "сентября", "октября", "ноября", "декабря"
        )
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val sumOperationText = result.data?.getStringExtra("key1")
                binding.sumContainer.value.text = sumOperationText
            }
        }

        launcherType = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val typeOperationCheck = result.data?.getStringExtra("key2")
                binding.typeContainer.value.text = typeOperationCheck
            }
        }

        launcherCategory = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val typeCategoryCheck = result.data?.getStringExtra("key3")
                binding.categoryContainer.value.text = typeCategoryCheck
            }
        }

        val sumContainer: ConstraintLayout = findViewById(R.id.sum_container)
        val typeContainer: ConstraintLayout = findViewById(R.id.type_container)
        val categoryContainer: ConstraintLayout = findViewById(R.id.category_container)
        val dateContainer: ConstraintLayout = findViewById(R.id.date_container)

        binding.sumContainer.value.text = intent.getStringExtra("ENTER_SUM_SESSION").toString() + " " + getString(R.string.rub_symbol)
        binding.typeContainer.type.text = getString(R.string.type_text)
        binding.typeContainer.value.text = (intent.getSerializableExtra("ENTER_TYPE_OPERATION") as CategoryType).typeLocalizedName
        binding.categoryContainer.type.text = getString(R.string.category)
        binding.categoryContainer.value.text = intent.getStringExtra("SELECT_OPERATION_CATEGORY").toString()
        binding.dateContainer.type.text = getString(R.string.date_operation)
        binding.dateContainer.value.text = "${calendar.get(Calendar.DAY_OF_MONTH)} " + "${months.get(calendar.get(Calendar.MONTH))}"
        binding.editOperationToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.editOperationToolbar.setNavigationOnClickListener {
            finish()
        }

        sumContainer.setOnClickListener {
            sumContainerBackData()
        }

        typeContainer.setOnClickListener {
            typeContainerBackData()
        }

        categoryContainer.setOnClickListener {
            categoryContainerBackData()
        }

        dateContainer.setOnClickListener {
            dataPickerSet()
        }

        binding.doneButtonBlackConfirm.setOnClickListener {
        }
    }

    fun dataPickerSet() {
        val builder = MaterialDatePicker.Builder.datePicker()
            .also {
                title = "Pick Date"
            }
        val datePicker = builder.build()
        datePicker.addOnPositiveButtonClickListener {
            calendar.time = Date(it)
            binding.dateContainer.value.text = "${calendar.get(Calendar.DAY_OF_MONTH)} " + "${months.get(calendar.get(Calendar.MONTH))}"
        }
        datePicker.show(supportFragmentManager, "MyTAG")
    }

    fun sumContainerBackData() {
        launcher?.launch(
            Intent(this, EnterOperationSumActivity::class.java)
                .putExtra("checkedActivity", "true")
                .putExtra("value", binding.sumContainer.value.text.toString().replace(" ₽", ""))
        )
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun typeContainerBackData() {
        launcherType?.launch(
            Intent(this, SelectOperationTypeActivity::class.java)
                .putExtra("checkedActivity", "true")
                .putExtra("sumComponent", binding.sumContainer.value.text.toString().replace(" ₽", ""))
                .putExtra("typeComponent", binding.typeContainer.value.text.toString())
        )
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun categoryContainerBackData() {
        launcherCategory?.launch(
            Intent(this, SelectOperationCategoryActivity::class.java)
                .putExtra("checkedActivity", "true")
                .putExtra("sumComponent", binding.sumContainer.value.toString())
        )
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}