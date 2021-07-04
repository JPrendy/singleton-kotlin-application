package com.example.singleton_kotlin_application

object Singleton {
    init {
        println("Singleton initialized")
    }

    var message = "Kotlin rock"

    fun showMessage(): String {
        return message
        println(message)
    }
}

class Test {
    init {
        Singleton.showMessage()
    }
}

fun main() {
    Singleton.showMessage()
    Singleton.message = "Kotlin is cool"

    val test = Test()
}