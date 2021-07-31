package com.example.practicetask1

import com.example.practicetask1.kotlinPart1.Book
import com.example.practicetask1.kotlinPart1.Magazine
import com.example.practicetask1.kotlinPart1.Publication
import org.junit.Test

class KotlinPart1Test {

    private val firstBook = Book(100f, 4353)
    private val secondBook = Book(100f, 4353)
    private val magazine = Magazine(10f, 542)

    @Test
    fun test() {
        displayInfo()
        compareBooks()
    }

    fun displayInfo() {
        displayPublicationInfo(firstBook)
        displayPublicationInfo(secondBook)
        displayPublicationInfo(magazine)
    }

    fun compareBooks() {
        val linksResult = firstBook === secondBook
        val equalsResult = firstBook.equals(secondBook)
        println("Links comparing: $linksResult" +
                "\nEquals comparing: $equalsResult")
    }

    fun displayPublicationInfo(publication: Publication) {
        val info = "Publication: ${publication.javaClass.simpleName}\n" +
                "Type: ${publication.getType()}\n" +
                "Words count: ${publication.wordCount}\n" +
                "Price: ${publication.getPriceString()}.\n"
        println(info)
    }
}