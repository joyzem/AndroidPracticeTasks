package com.example.practicetask1.kotlinPart1

class Magazine(override var price: Float, override val wordCount: Int) : Publication {
    override fun getType(): String = "Magazine"
}