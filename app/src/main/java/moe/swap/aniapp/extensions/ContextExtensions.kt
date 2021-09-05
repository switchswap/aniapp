package moe.swap.aniapp.extensions

import android.content.Context
import android.widget.Toast

fun Context.longToast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_LONG).show()
}

fun Context.toast(s: String) {
    Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
}