package ru.sirius.siriuswallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SiriusWallet)
        setContentView(R.layout.activity_splash)
        val intent = Intent(this, OnBoardingActivity::class.java)
        this.startActivity(intent)
    }
}