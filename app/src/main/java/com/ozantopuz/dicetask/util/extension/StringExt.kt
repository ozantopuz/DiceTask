package com.ozantopuz.dicetask.util.extension

fun String?.ignoreNull(defaultValue: String = ""): String = this ?: defaultValue
