package com.example.singleton_kotlin_application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        val personOne = StaticClass(2)
        personOne.getPoints()
    }
}