package com.example.stresscoping

import android.content.Context
import android.util.TypedValue

fun Int.dpToPx(context: Context): Int {
    val resources = context.resources
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        resources.displayMetrics
    ).toInt()
}