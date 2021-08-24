package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationTypeBinding
import ru.sirius.siriuswallet.model.CategoryType


class SelectOperationTypeActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationTypeBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationTypeBinding.inflate(layoutInflater)
    }

    private var checkActivity: String = "false"
    private var enterOperationSum = ""
    private var typeOfOperation: CategoryType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (checkActivity == "true") {
            if (intent.getStringExtra("typeComponent").equals("Пополнение")) {
                binding.radioButtonIncome.isEnabled = true
            } else {
                binding.radioButtonExpense.isEnabled = true
            }
        }

        checkActivity = intent.getStringExtra("checkedActivity").toString()
        enterOperationSum = intent.getStringExtra("ENTER_SUM_SESSION").toString()

        binding.selectType.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.selectType.setNavigationOnClickListener {
            finish()
        }
        binding.firstRg.jumpDrawablesToCurrentState()

        binding.firstRg.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                binding.doneButton.isEnabled = true
                binding.doneButton.setTextColor(resources.getColor(R.color.white))
                typeOfOperation = if (binding.firstRg.checkedRadioButtonId == binding.radioButtonIncome.id) {
                    CategoryType.INCOME
                } else {
                    CategoryType.OUTCOME
                }
                Log.i("radiogroup", "$typeOfOperation")
            }
        }
        binding.doneButton.setOnClickListener {
            goToSelectOperationActivity()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun goToSelectOperationActivity() {
        if (checkActivity != "true") {
            val intent = Intent(this, SelectOperationCategoryActivity::class.java).apply {
                putExtra("ENTER_SUM_SESSION", intent.getStringExtra("ENTER_SUM_SESSION").toString())
                putExtra("ENTER_TYPE_OPERATION", typeOfOperation)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        } else {
            val intent = Intent(this, SelectOperationCategoryActivity::class.java).apply {
                putExtra("ENTER_SUM_SESSION", intent.getStringExtra("sumComponent"))
                putExtra("ENTER_TYPE_OPERATION", typeOfOperation)
            }
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }

    }
}