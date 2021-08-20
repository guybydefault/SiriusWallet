package ru.sirius.siriuswallet

import android.app.Activity

fun Activity.getContainer(): SiriusWalletContainer {
    return (application as SiriusWalletApplication).container
}