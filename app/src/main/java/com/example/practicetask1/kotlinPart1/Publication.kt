package com.example.practicetask1.kotlinPart1

import java.text.NumberFormat
import java.util.Locale

interface Publication {
    var price: Float
    val wordCount: Int

    fun getType(): String

    fun getPriceString(): String {
        val format = NumberFormat.getCurrencyInstance(Locale.GERMANY)
        return format.format(price)
    }
}
