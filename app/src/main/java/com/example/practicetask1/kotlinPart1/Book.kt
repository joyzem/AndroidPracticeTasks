package com.example.practicetask1.kotlinPart1

class Book(override var price: Float, override val wordCount: Int) : Publication {

    companion object {
        const val FLASH_FICTION_WORDS_LIMIT = 1000
        const val SHORT_STORY_WORDS_LIMIT = 7500
        const val FLASH_FICTION = "Flash fiction"
        const val SHORT_STORY = "Short story"
        const val NOVEL = "Novel"
    }

    override fun getType(): String = when {
        wordCount < FLASH_FICTION_WORDS_LIMIT -> FLASH_FICTION
        wordCount < SHORT_STORY_WORDS_LIMIT -> SHORT_STORY
        else -> NOVEL
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (price != other.price) return false
        if (wordCount != other.wordCount) return false

        return true
    }

    override fun hashCode(): Int {
        var result = price.hashCode()
        result = 31 * result + wordCount
        return result
    }
}