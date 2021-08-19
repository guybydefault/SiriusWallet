package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import ru.sirius.siriuswallet.databinding.ActivityOnboardingBinding

class EnterOperationSumActivity : AppCompatActivity() {

    private val binding: ActivityOnboardingBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_operation_sum)
    }

    private fun startActivity(account: GoogleSignInAccount?) {
        if (account != null) {
            binding.identityButton.visibility = View.INVISIBLE
            val intent = Intent(this, WalletInfoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}