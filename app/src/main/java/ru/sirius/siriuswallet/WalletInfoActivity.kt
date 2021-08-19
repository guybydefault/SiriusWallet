package ru.sirius.siriuswallet

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.sirius.siriuswallet.databinding.ActivityWalletInfoBinding

class WalletInfoActivity : AppCompatActivity() {

    private val binding: ActivityWalletInfoBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityWalletInfoBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupListeners()
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.backArrowBtn.setOnClickListener { onBackClick() }
        binding.settingsBtn.setOnClickListener { onSettingsClick() }
        binding.addOperationBtn.setOnClickListener { onAddOperationBtnClick() }
    }


    fun onBackClick() {
        Toast.makeText(this, "Back arrow click", Toast.LENGTH_LONG).show()
    }

    fun onSettingsClick() {
        Toast.makeText(this, "Settings", Toast.LENGTH_LONG).show()
    }

    fun onAddOperationBtnClick() {
        val intent = Intent(this, EnterOperationSumActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(this);
        startActivity(intent, options.toBundle())
    }
}