package ru.sirius.siriuswallet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import ru.sirius.siriuswallet.databinding.ActivityEditOperationBinding

class EditOperationActivity : AppCompatActivity() {
    private val binding: ActivityEditOperationBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEditOperationBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sumContainer: ConstraintLayout = findViewById(R.id.sum_container)
        val typeContainer: ConstraintLayout = findViewById(R.id.type_container)
        val categoryContainer: ConstraintLayout = findViewById(R.id.category_container)
        val dateContainer: ConstraintLayout = findViewById(R.id.date_container)

        binding.typeContainer.type.text = getString(R.string.type_text)
        binding.typeContainer.value.text = getString(R.string.income_label)
        binding.categoryContainer.type.text = getString(R.string.category)
        binding.categoryContainer.value.text = getString(R.string.category_text)
        binding.dateContainer.value.text = getString(R.string.date_operation)
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
            Toast.makeText(this, "dateContainer", Toast.LENGTH_LONG).show()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}