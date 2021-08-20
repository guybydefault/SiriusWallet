package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import ru.sirius.siriuswallet.databinding.ActivityEnterOperationSumBinding

class EnterOperationSumActivity : AppCompatActivity() {

    private val binding: ActivityEnterOperationSumBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityEnterOperationSumBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.enterSum.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.enterSum.setNavigationOnClickListener {
            finish()
        }

        setupListeners()

        binding.doneButton.setOnClickListener {
            if (validateUserName()) {
                startActivity()
            }

        }


    }

    inner class TextFieldValidation(private val view: View) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            validateUserName()
        }
    }

    private fun validateUserName(): Boolean {
        if (binding.sumOperation.text.toString().trim().isEmpty()) {
            binding.sumOperationInputLayout.error = "Обязательно к заполнению!"
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


    private fun startActivity() {
        val intent = Intent(this, SelectOperationTypeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }


}