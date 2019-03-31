package com.farmanlab.commonextensions

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

// region view
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)
// endregion

// region activity
fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.getRootView() = findViewById<View>(android.R.id.content)

// endregion

// region fragment
fun Fragment.toastShort(message: String) {
    activity?.let { Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
}

fun Fragment.toastLong(message: String) {
    activity?.let { Toast.makeText(activity, message, Toast.LENGTH_LONG).show() }
}
// endregion
