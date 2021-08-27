package ru.sirius.siriuswallet

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast


fun showToast(context: Context, image: Int, originalMsg: String, duration: Int) {
    var message = if (originalMsg.contains("timeout")) "Превышено время ожидания от сервера" else "Ошибка получения данных с сервера"
    if (ApplicationConstants.USE_DEFAULT_TOASTS) {
        Toast.makeText(context, message, duration).show()
    } else {
        val toast = Toast(context)
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.toast, null)
        val toastMessage = view.findViewById<TextView>(R.id.toast_text)
        val toastImage = view.findViewById<ImageView>(R.id.toast_icon)
        toastMessage.text = message
        toastImage.setImageResource(image)
        toast.view = view
        toast.setGravity(Gravity.TOP, 0, 70)
        toast.duration = duration
        toast.show();
    }
}

