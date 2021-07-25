package com.example.singleton_kotlin_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.TextView

@Suppress("UNUSED_PARAMETER")
class MainActivity : AppCompatActivity() {

    var globalVariable = "global variable from Main Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println(Singleton.showMessage())

        val staticClassExample = StaticClass(10)
        println(staticClassExample.score)
        staticClassExample.staticScore = 10

        globalVariable =  "global variable from Main Activity, assigned with a new value to be used for intents"

        Singleton.message = "Singletons are cool, assigned value from Main Activity"

        val singletonValueTextView = findViewById<TextView>(R.id.singletonValue)
        singletonValueTextView.text = staticClassExample.getPoints().toString()

        val staticValueTextView = findViewById<TextView>(R.id.staticValue)
        staticValueTextView.text = Singleton.showMessage()

        val intentExampleTextView = findViewById<TextView>(R.id.intentValue)
        intentExampleTextView.text = globalVariable
    }

    fun changeActivity(view: View) {
        // This is a way of switching to a new activity without passing any other information.
        // val intent = Intent(this, FinalActivity::class.java)

        // This intent passes value from a global variable
        val intent = Intent(this, FinalActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, globalVariable)
        }
        startActivity(intent)
    }
}