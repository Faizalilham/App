package com.faizal.newsapp.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun formatDateTimeLegacy(input: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    inputFormat.timeZone = TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("dd MMM yyyy,HH:mm", Locale("id", "ID"))
    outputFormat.timeZone = TimeZone.getDefault()

    val date = inputFormat.parse(input)
    return outputFormat.format(date!!)
}