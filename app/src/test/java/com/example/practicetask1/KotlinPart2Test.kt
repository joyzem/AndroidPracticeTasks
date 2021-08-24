package com.example.practicetask1

import com.example.practicetask1.kotlinPart2.*
import org.junit.Test

class KotlinPart2Test {

    private val listOfUsers = getUsersList()

    @Test
    fun `Create user's instance and call lazy prop`() {
        callStartTimePropTwoTimes()
    }

    @Test
    fun `Get users with full access`() {
        println(getUsersWithFullAccess(listOfUsers))
    }

    @Test
    fun `Convert users list to names list with first and last element printed`() {
        convertToNamesListAndPrintFirstAndLastNames(listOfUsers)
    }

    @Test
    fun `Check if user is older 18`() {
        getUserWithAge(19).isOlderThanEighteen()
        try {
            getUserWithAge(16).isOlderThanEighteen()
        } catch (e: IllegalArgumentException) {
            println("Elder")
        }
    }

    @Test
    fun `Authentificating users`() {
        displayAuthStatus(getUserWithAge(17))
        displayAuthStatus(getUserWithAge(19))
    }

    @Test
    fun `Action class testing`() {
        doAction(Action.Registration())
        doAction(Action.Logout())
        doAction(Action.Login(getUserWithAge(19)))
    }

    private fun getUsersList(): List<User> {
        return listOf(
            User(1, "Jack", 12, Type.FULL),
            User(2, "John", 11, Type.DEMO),
            User(3, "Lee", 19, Type.FULL)
        )
    }

    private fun getUserWithAge(age: Int): User {
        return User(10, "Mark", age = age, Type.FULL)
    }
}
