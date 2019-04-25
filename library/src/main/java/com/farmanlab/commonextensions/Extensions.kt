package com.farmanlab.commonextensions

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.nio.charset.Charset

// region common
fun Context.loadColor(@ColorRes res: Int): Int = ContextCompat.getColor(this, res)

fun Context.readFileFromAssets(fileName: String, charSet: Charset = Charset.defaultCharset()): String {
    val inputStream = assets.open(fileName)
    inputStream.bufferedReader(charSet).useLines { return it.map { row -> row }.joinToString("\n") }
}
// endregion

// region view
fun ViewGroup.inflate(@LayoutRes layout: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layout, this, attachToRoot)

fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}
// endregion

// region activity
fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.getRootView() = findViewById<View>(android.R.id.content)

fun Activity.showKeyboard() {
    findViewById<View>(android.R.id.content).showKeyboard()
}

fun Activity.hideKeyboard(): Boolean = findViewById<View>(android.R.id.content).hideKeyboard()

fun Fragment.showKeyboard() {
    activity?.showKeyboard()
}

fun Fragment.hideKeyboard(): Boolean = activity?.hideKeyboard() ?: false

// endregion

// region fragment
fun Fragment.toastShort(message: String) {
    activity?.let { Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
}

fun Fragment.toastLong(message: String) {
    activity?.let { Toast.makeText(activity, message, Toast.LENGTH_LONG).show() }
}
// endregion
