package ru.sirius.siriuswallet

import android.app.Application

class SiriusWalletApplication: Application()  {
    lateinit var container: SiriusWalletContainer

    override fun onCreate() {
        container = SiriusWalletContainer(applicationContext)
        super.onCreate()
    }
}