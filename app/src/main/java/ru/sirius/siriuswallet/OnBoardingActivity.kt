package ru.sirius.siriuswallet

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import ru.sirius.siriuswallet.databinding.ActivityOnboardingBinding


class OnBoardingActivity : AppCompatActivity() {

    private val binding: ActivityOnboardingBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityOnboardingBinding.inflate(layoutInflater)
    }

    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            val account = task.result

            startSecondActivity(account)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.identityButton.setOnClickListener {
            loginResultHandler.launch(getSignInIntent())
        }
    }

    override fun onStart() {
        super.onStart()

        startSecondActivity(GoogleSignIn.getLastSignedInAccount(this))
    }

    private fun getSignInIntent(): Intent {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        return mGoogleSignInClient.signInIntent
    }

    private fun startSecondActivity(account: GoogleSignInAccount?) {
        if (account != null) {
            binding.identityButton.visibility = View.INVISIBLE
            val intent = Intent(this, WalletInfoActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

}