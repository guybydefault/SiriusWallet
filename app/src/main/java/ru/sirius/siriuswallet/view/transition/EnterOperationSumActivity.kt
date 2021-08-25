package ru.sirius.siriuswallet.view.transition

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ru.sirius.siriuswallet.R
import ru.sirius.siriuswallet.databinding.ActivityEnterOperationSumBinding
import ru.sirius.siriuswallet.model.ActivityConst.CHECKED_ACTIVITY_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.ENTER_SUM_SESSION_FLAG
import ru.sirius.siriuswallet.model.ActivityConst.RESULT_SUM_COMPONENT_FLAG


class EnterOperationSumActivity : AppCompatActivity() {

    private val binding: ActivityEnterOperationSumBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEnterOperationSumBinding.inflate(layoutInflater)
    }
    private var checkActivity: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.enterSum.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.enterSum.setNavigationOnClickListener {
            finish()
        }
        setupListeners()
        checkActivity = intent.getBooleanExtra(CHECKED_ACTIVITY_FLAG, false)
        if (checkActivity) {
            binding.sumOperation.setText(intent.getStringExtra("value"))
        }

        binding.doneButton.setOnClickListener {
            if (validateEnterSum() && !checkActivity) {
                goToSelectOperationType()
            } else if (validateEnterSum()) {
                val i = Intent()
                i.putExtra(RESULT_SUM_COMPONENT_FLAG, binding.sumOperation.text.toString() + " ₽")
                setResult(RESULT_OK, i)
                finish()
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            }
        }
    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            validateEnterSum()
        }
    }

    private fun validateEnterSum(): Boolean {
        if (binding.sumOperation.text.toString().trim().isEmpty()) {
            binding.sumOperationInputLayout.error = getString(R.string.warning_text_input)
            binding.sumOperation.requestFocus()
            return false
        } else {
            binding.sumOperationInputLayout.isErrorEnabled = false
        }
        return true
    }

    private fun setupListeners() {
        binding.sumOperation.addTextChangedListener(TextFieldValidation(binding.sumOperation))
    }

    private fun goToSelectOperationType() {
        val intent = Intent(this, SelectOperationTypeActivity::class.java).apply {
            putExtra(ENTER_SUM_SESSION_FLAG, binding.sumOperation.text.toString())
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}