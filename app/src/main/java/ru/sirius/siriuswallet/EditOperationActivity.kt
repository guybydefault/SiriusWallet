package ru.sirius.siriuswallet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.datepicker.MaterialDatePicker
import ru.sirius.siriuswallet.databinding.ActivityEditOperationBinding
import java.util.*

class EditOperationActivity : AppCompatActivity() {
    private val binding: ActivityEditOperationBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEditOperationBinding.inflate(layoutInflater)
    }

    val months =
        arrayOf(
            "января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа",
            "сентября", "октября", "ноября", "декабря"
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent

        val sumContainer: ConstraintLayout = findViewById(R.id.sum_container)
        val typeContainer: ConstraintLayout = findViewById(R.id.type_container)
        val categoryContainer: ConstraintLayout = findViewById(R.id.category_container)
        val dateContainer: ConstraintLayout = findViewById(R.id.date_container)

        binding.sumContainer.value.text = i.getStringExtra("ENTER_SUM_SESSION")!! + " " +
                getString(R.string.rub_symbol)
        binding.typeContainer.type.text = getString(R.string.type_text)
        binding.typeContainer.value.text = i.getStringExtra("ENTER_TYPE_OPERATION")!!
        binding.categoryContainer.type.text = getString(R.string.category)
        binding.categoryContainer.value.text = i.getStringExtra("SELECT_OPERATION_CATEGORY")!!
        binding.dateContainer.type.text = getString(R.string.date_operation)
        binding.dateContainer.value.text = getString(R.string.date_operation_text)

        binding.editOperationToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.editOperationToolbar.setNavigationOnClickListener {
            finish()
        }

        sumContainer.setOnClickListener {
            Toast.makeText(this, "sumContainer", Toast.LENGTH_LONG).show()
        }

        typeContainer.setOnClickListener {
            Toast.makeText(this, "typeContainer", Toast.LENGTH_LONG).show()
        }

        categoryContainer.setOnClickListener {
            Toast.makeText(this, "categoryContainer", Toast.LENGTH_LONG).show()
        }

        dateContainer.setOnClickListener {
            // Create the date picker builder and set the title
            val builder = MaterialDatePicker.Builder.datePicker()
                .also {
                    title = "Pick Date"
                }


            // create the date picker
            val datePicker = builder.build()

            // set listener when date is selected
            datePicker.addOnPositiveButtonClickListener {

                // Create calendar object and set the date to be that returned from selection
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.time = Date(it)
                binding.dateContainer.value.text = "${calendar.get(Calendar.DAY_OF_MONTH)} " +
                        "${months.get(calendar.get(Calendar.MONTH))}"
//calendar.get(Calendar.MONTH) + 1
            }

            datePicker.show(supportFragmentManager, "MyTAG")

        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}