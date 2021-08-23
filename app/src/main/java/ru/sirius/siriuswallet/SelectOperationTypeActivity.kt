package ru.sirius.siriuswallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationTypeBinding


class SelectOperationTypeActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationTypeBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationTypeBinding.inflate(layoutInflater)
    }
    private var enterSum = ""
    private var typeOfOperation = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val i = intent
        enterSum = i.getStringExtra("ENTER_SUM_SESSION")!!



        binding.selectType.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.selectType.setNavigationOnClickListener {
            finish()
        }
        binding.firstRg.jumpDrawablesToCurrentState()

        binding.firstRg.setOnCheckedChangeListener { _, checkedId ->
            findViewById<RadioButton>(checkedId)?.apply {
                binding.doneButton.isEnabled = true

                binding.doneButton.setTextColor(resources.getColor(R.color.white))
                typeOfOperation = if (binding.firstRg.checkedRadioButtonId == 0) {
                    getString(R.string.income_label)
                } else {
                    getString(R.string.expense_label)
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
        val intent = Intent(this, SelectOperationCategoryActivity::class.java)
        intent.putExtra("ENTER_SUM_SESSION", enterSum)
        intent.putExtra("ENTER_TYPE_OPERATION", typeOfOperation)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }


}