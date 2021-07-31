package com.example.practicetask1.kotlinPart1

interface Publication {
    var price: Float
    val wordCount: Int

    fun getType(): String
}