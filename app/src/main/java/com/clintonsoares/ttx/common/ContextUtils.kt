package com.clintonsoares.ttx.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import dagger.hilt.android.internal.managers.ViewComponentManager

fun Fragment.hideKeyboard() = view?.hideKeyboard()

fun Context.hideKeyboard() {
    if (this !is Activity && this !is Fragment && this !is ViewComponentManager.FragmentContextWrapper) return
    val view = when (this) {
        is Activity -> currentFocus
        is Fragment -> view
        //is ViewComponentManager.FragmentContextWrapper -> getFragment().view
        else -> null
    }
    view?.hideKeyboard()

}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}