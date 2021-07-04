package com.example.singleton_kotlin_application

class StaticClass(score: Int) {
    var score = 0
    var score2 = 5

    init {
        this.score = score
    }

    fun getPoints(): Int {
        return score2
    }
}