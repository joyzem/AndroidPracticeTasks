package com.example.practicetask1

import com.example.practicetask1.kotlinPart1.Book
import com.example.practicetask1.kotlinPart1.Magazine
import com.example.practicetask1.kotlinPart1.Publication
import org.jetbrains.annotations.NotNull
import org.junit.Test

class KotlinPart1Test {

    private val firstBook = Book(100f, 4353)
    private val secondBook = Book(100f, 4353)
    private val magazine = Magazine(10f, 542)

    private val notNullBook: Book? = Book(100f, 1100)
    private val nullBook: Book? = null

    private val sum = { firstValue: Int, secondValue: Int ->
        println(firstValue + secondValue)
    }

    @Test
    fun test() {
        displayInfo()
        compareBooks()

        notNullBook?.let { buy(it) }
        nullBook?.let { buy(it) }

        sum(3, 2)
        sum(-4, 2)
        sum(0, 3)
        sum(2, 6)
    }

    fun buy(@NotNull publication: Publication) {
        println("The purchase is complete. The purchase amount was ${publication.getPriceString()}\n")
    }

    fun displayInfo() {
        displayPublicationInfo(firstBook)
        displayPublicationInfo(secondBook)
        displayPublicationInfo(magazine)
    }

    fun compareBooks() {
        val linksResult = firstBook === secondBook
        val equalsResult = firstBook.equals(secondBook)
        println(
            "Links comparing: $linksResult" +
                "\nEquals comparing: $equalsResult\n"
        )
    }

    fun displayPublicationInfo(publication: Publication) {
        val info = "Publication: ${publication.javaClass.simpleName}\n" +
            "Type: ${publication.getType()}\n" +
            "Words count: ${publication.wordCount}\n" +
            "Price: ${publication.getPriceString()}.\n"
        println(info)
    }
}
