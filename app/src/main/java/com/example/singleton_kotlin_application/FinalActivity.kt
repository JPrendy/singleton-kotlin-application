package com.example.singleton_kotlin_application

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FinalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final)

        //Important Intent, we getting the value we passed over from `MainActivity`
        var message = intent.getStringExtra(EXTRA_MESSAGE)

        val staticClassFinalExample = StaticClass(2)

        println(Singleton.showMessage())

        val staticFinalExampleTextView = findViewById<TextView>(R.id.staticFinalExample)
        staticFinalExampleTextView.text = "This value should be 10, but isn't " + staticClassFinalExample.getPoints().toString()

        val singletonFinalExampleTextView = findViewById<TextView>(R.id.singletonFinalExample)
        singletonFinalExampleTextView.text = Singleton.showMessage()

        val intentFinalExampleTextView = findViewById<TextView>(R.id.intentFinalExample)
        intentFinalExampleTextView.text = message
    }
}