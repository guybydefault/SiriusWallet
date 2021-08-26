package ru.sirius.siriuswallet

object ToastConfig {
    val toasty = Toasty.Config.getInstance()
        .tintIcon(boolean tintIcon) // optional (apply textColor also to the icon)
        .setToastTypeface(@NonNull Typeface typeface) // optional
        .setTextSize(int sizeInSp) // optional
        .allowQueue(boolean allowQueue) // optional (prevents several Toastys from queuing)
        .apply(); // required
}