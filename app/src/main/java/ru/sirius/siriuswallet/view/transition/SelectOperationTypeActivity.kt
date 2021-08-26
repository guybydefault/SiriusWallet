package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import es.dmoral.toasty.Toasty
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationTypeBinding
import ru.sirius.siriuswallet.model.ActivityConst.CHECKED_ACTIVITY_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.ENTER_SUM_SESSION_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.ENTER_TYPE_OPERATION_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.RESULT_TYPE_COMPONENT_FLAG
import ru.sirius.siriuswallet.model.CategoryType

class SelectOperationTypeActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationTypeBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationTypeBinding.inflate(layoutInflater)
    }

    private var checkActivity = false
    private var enterOperationSum = ""
    private var typeOfOperation: CategoryType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initInputParameters()

        val toast: Toast? = Toasty.custom(this, "Some trouble", R.drawable.ic_cog, R.color.blackColorButton, Toasty.LENGTH_LONG, true, true)
        toast?.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
        toast?.show()

        initNavigationBar()

        binding.firstRg.jumpDrawablesToCurrentState()
        binding.firstRg.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                binding.doneButton.isEnabled = true
                binding.doneButton.setTextColor(ContextCompat.getColor(context, R.color.white))
                typeOfOperation = if (binding.firstRg.checkedRadioButtonId == binding.radioButtonIncome.id) {
                    CategoryType.INCOME
                } else {
                    CategoryType.OUTCOME
                }
            }
        }
        setupDoneButton()
    }

    private fun initInputParameters() {
        enterOperationSum = intent.getStringExtra(ENTER_SUM_SESSION_FLAG).toString()
        checkActivity = intent.getBooleanExtra(CHECKED_ACTIVITY_FLAG, false)
        val checkTypeActivity = intent.getStringExtra(RESULT_TYPE_COMPONENT_FLAG).toString()
        if (checkActivity) {
            if (checkTypeActivity == "Пополнение") {
                binding.radioButtonIncome.isChecked = true
            } else {
                binding.radioButtonExpense.isChecked = true
            }
        }
    }

    private fun initNavigationBar() {
        binding.selectType.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.selectType.setNavigationOnClickListener {
            finish()
        }
    }

    private fun setupDoneButton() {
        binding.doneButton.setOnClickListener {
            toCategorySelectionActivity()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

    private fun toCategorySelectionActivity() {
        if (!checkActivity) {
            startActivityOperation()
        } else {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivityOperation()
        }
    }

    private fun startActivityOperation() {
        val intent = Intent(this, SelectOperationCategoryActivity::class.java).apply {
            putExtra(ENTER_SUM_SESSION_FLAG, intent.getStringExtra(ENTER_SUM_SESSION_FLAG))
            putExtra(ENTER_TYPE_OPERATION_FLAG, typeOfOperation)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }
}