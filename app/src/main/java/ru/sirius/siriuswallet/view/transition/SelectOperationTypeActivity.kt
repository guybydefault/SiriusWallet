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

    private var checkActivity = false
    private var enterOperationSum = ""
    private var typeOfOperation: CategoryType? = null
    private val enterSumFlag = "ENTER_SUM_SESSION"
    private val enterTypeFlag = "ENTER_TYPE_OPERATION"
    private val checkedActivityFlag = "CHECKED_ACTIVITY"
    private val backSumComponentFlag = "SUM_COMPONENT"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        enterOperationSum = intent.getStringExtra(enterSumFlag).toString()
        checkActivity = intent.getBooleanExtra(checkedActivityFlag, false)
        val checkTypeActivity = intent.getStringExtra("typeComponent").toString()

        if (checkActivity) {
            if (checkTypeActivity == "Пополнение") {
                binding.radioButtonIncome.isChecked = true
            } else {
                binding.radioButtonExpense.isChecked = true
            }
        }

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
        if (!checkActivity) {
            startActivityOperation(enterSumFlag)
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivityOperation(backSumComponentFlag)
        }
    }

    private fun startActivityOperation(value: String) {
        val intent = Intent(this, SelectOperationCategoryActivity::class.java).apply {
            putExtra(enterSumFlag, intent.getStringExtra(value).toString())
            putExtra(enterTypeFlag, typeOfOperation)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}