package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.sirius.siriuswallet.databinding.ActivityEditOperationBinding
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationTypeBinding

class EditOperationActivity : AppCompatActivity() {
    private val binding: ActivityEditOperationBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEditOperationBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}