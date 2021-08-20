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


        binding.editOperationToolbar.setNavigationIcon(R.drawable.ic_arrow_left)
        binding.editOperationToolbar.setNavigationOnClickListener {
            backToEnterOperationActivity()
        }
    }

    override fun finish() {
        super.finish()
        backToEnterOperationActivity()
    }


    private fun backToEnterOperationActivity() {
        val intent = Intent(this, SelectOperationTypeActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}