package com.example.chequeapp.utils.extensions

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

fun String.toFloatOrNullWithComma(): Float? =
    this.toFloatOrNull() ?:
    NumberFormat.getInstance(Locale.FRANCE).parse(this)?.toFloat()

fun EditText.setOnValueChangedListener(f: (String) -> Unit) {
    with(this) {
        setOnFocusChangeListener { _, b ->
            if (!b) {
                val text = text?.toString() ?: ""
                f(text)
            }
        }
        setOnEditorActionListener(
            object : TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        val token = v?.text?.toString()
                        f(token ?: "")
                        return true
                    }
                    return false
                }
            },
        )
    }
}