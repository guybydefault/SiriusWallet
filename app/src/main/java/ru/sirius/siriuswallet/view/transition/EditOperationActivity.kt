package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.datepicker.MaterialDatePicker
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.ActivityEditOperationBinding
import ru.sirius.siriuswallet.getContainer
import ru.sirius.siriuswallet.model.ActivityConst.BACK_SUM_COMPONENT_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.CHECKED_ACTIVITY_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.ENTER_SUM_SESSION_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.ENTER_TYPE_OPERATION_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.RESULT_OPERATION_COMPONENT_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.RESULT_SUM_COMPONENT_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.RESULT_TYPE_COMPONENT_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.SELECT_OPERATION_CATEGORY_FLAG
import ru.sirius.siriuswallet.model.CategoryType
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class EditOperationActivity : AppCompatActivity() {
    private val binding: ActivityEditOperationBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEditOperationBinding.inflate(layoutInflater)
    }
    private var launcher: ActivityResultLauncher<Intent>? = null
    private var launcherCategory: ActivityResultLauncher<Intent>? = null

    private lateinit var editOperationViewModel: EditOperationViewModel

    val months =
        arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа",
            "сентября", "октября", "ноября", "декабря"
        )
    val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        editOperationViewModel = getContainer().editOperationViewModel
        setupEditActivitiesForResult()
        setupErrorToasts()

        val sumContainer: ConstraintLayout = findViewById(R.id.sum_container)
        val typeContainer: ConstraintLayout = findViewById(R.id.type_container)
        val categoryContainer: ConstraintLayout = findViewById(R.id.category_container)
        val dateContainer: ConstraintLayout = findViewById(R.id.date_container)

        val sum = intent.getStringExtra(ENTER_SUM_SESSION_FLAG)
        editOperationViewModel.amount = sum!!.toDouble().toBigDecimal()
        updateSum(sum)
        binding.typeContainer.type.text = getString(R.string.type_text)
        binding.typeContainer.value.text = (intent.getSerializableExtra(ENTER_TYPE_OPERATION_FLAG) as CategoryType).typeLocalizedName
        binding.categoryContainer.type.text = getString(R.string.category)
        updateCategoryId(intent.getIntExtra(SELECT_OPERATION_CATEGORY_FLAG, 0))
        binding.dateContainer.type.text = getString(R.string.date_operation)
        editOperationViewModel.dateTime = LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.of("UTC"))
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
        editOperationViewModel.category.observe(this) {
            binding.categoryContainer.value.text = it.name
        }

        binding.doneButtonBlackConfirm.setOnClickListener {
            val editOperationViewModel = editOperationViewModel
            editOperationViewModel.createOperation()
        }

        editOperationViewModel.successfullyCreatedOperation.observe(this) {
            Toast.makeText(this, "Операция успешно добавлена", Toast.LENGTH_LONG).show()
            finish()
        }
    }

    private fun setupErrorToasts() {
        editOperationViewModel.err.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateCategoryId(categoryId: Int) {
        this.editOperationViewModel.selectedCategoryId = categoryId
    }

    private fun updateSum(sum: String) {
        binding.sumContainer.value.text = sum + " " + getString(R.string.rub_symbol)
    }

    private fun setupEditActivitiesForResult() {
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val sumOperationText = result.data!!.getStringExtra(RESULT_SUM_COMPONENT_FLAG)!!
                updateSum(sumOperationText)
            }
        }

        launcherCategory = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val categoryId = result.data!!.getIntExtra(RESULT_OPERATION_COMPONENT_FLAG, 0)
                updateCategoryId(categoryId)
            }
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

    private fun sumContainerBackData() {
        launcher?.launch(
            Intent(this, EnterOperationSumActivity::class.java)
                .putExtra(CHECKED_ACTIVITY_FLAG, true)
                .putExtra("value", binding.sumContainer.value.text.toString().replace(" ₽", ""))
        )
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun typeContainerBackData() {
        val intent = Intent(this, SelectOperationTypeActivity::class.java).apply {
            putExtra(CHECKED_ACTIVITY_FLAG, true)
            putExtra(BACK_SUM_COMPONENT_FLAG, binding.sumContainer.value.text.toString().replace(" ₽", ""))
            putExtra(RESULT_TYPE_COMPONENT_FLAG, binding.typeContainer.value.text.toString())
        }
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun categoryContainerBackData() {
        launcherCategory?.launch(
            Intent(this, SelectOperationCategoryActivity::class.java)
                .putExtra(CHECKED_ACTIVITY_FLAG, true)
                .putExtra(BACK_SUM_COMPONENT_FLAG, binding.sumContainer.value.toString())
        )
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}