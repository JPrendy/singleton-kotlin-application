package com.example.singleton_kotlin_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(Singleton.showMessage())
        val personOne = StaticClass(10)
        println(personOne.score)
        val textView = findViewById<TextView>(R.id.singletonValue)
        textView.text = personOne.score.toString()
        val textView2 = findViewById<TextView>(R.id.staticValue)
        textView2.text = Singleton.showMessage()
    }
}