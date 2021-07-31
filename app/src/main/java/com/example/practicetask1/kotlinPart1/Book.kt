package com.example.practicetask1.kotlinPart1

class Book(override var price: Float, override val wordCount: Int) : Publication {

    override fun getType(): String = when {
        wordCount < 1000 -> "Flash Fiction"
        wordCount < 7500 -> "Short Story"
        else -> "Novel"
    }
}