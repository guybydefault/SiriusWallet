package ru.sirius.siriuswallet

import android.graphics.Typeface
import androidx.annotation.NonNull
import es.dmoral.toasty.Toasty


object ToastConfig {
    val toasty = Toasty.Config.getInstance()
        .tintIcon(true) // optional (apply textColor also to the icon)
        .setTextSize(16) // optional
        .allowQueue(true) // optional (prevents several Toastys from queuing)
        .apply(); // required
}