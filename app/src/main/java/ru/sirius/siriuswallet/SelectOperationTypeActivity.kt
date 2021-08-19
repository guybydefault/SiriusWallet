package ru.sirius.siriuswallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import ru.sirius.siriuswallet.databinding.ActivitySelectOperationTypeBinding


class SelectOperationTypeActivity : AppCompatActivity() {
    private val binding: ActivitySelectOperationTypeBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivitySelectOperationTypeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun finish() {
        super.finish()
        val intent = Intent(this, EnterOperationSumActivity::class.java)
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }

}