package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        binding.doneButton.setOnClickListener {
            startActivity()
        }
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