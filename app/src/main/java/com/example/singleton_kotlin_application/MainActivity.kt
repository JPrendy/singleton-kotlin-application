package com.example.singleton_kotlin_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println(Singleton.showMessage())
        val personOne = StaticClass(10)
        println(personOne.score)
        personOne.score2 = 10
        val textView = findViewById<TextView>(R.id.singletonValue)
        textView.text = personOne.score.toString()
        val textView2 = findViewById<TextView>(R.id.staticValue)
        textView2.text = Singleton.showMessage()
    }

    fun changeActivity(view: View) {
        val intent = Intent(this, FinalActivity::class.java)
        startActivity(intent)
    }
}