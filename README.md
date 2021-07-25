# Singleton Kotlin application

## Description

A Kotlin application that goes into detail on why you would want to use Singleton pattern which ensure a class only has one instance, and provide a global point of access to it (global object) over something like global variables.

## Contents

- [Setup Steps](#setup-steps)
- [How to run the project locally](#how-to-run-the-project-locally)
- [Tools](#tools)
- [Update Dependencies](#update-dependencies)
- [Documentations](#documentations)
- [Releases](#releases)
- [Helpful resources](#helpful-resources)

## Setup Steps

A singleton’s two most common use cases are:

- To share data between two otherwise unrelated areas of your project.

- To share logic that doesn’t have associated state throughout the app. Keep in mind that singletons are not a data persistence solution. Data in singletons live only as long as your app is in memory.

**Avoid overusing singletons:** It’s tempting to use singletons as a solution for all your data sharing needs. While handy at first, overusing singletons will cause maintainability issues because many parts of your code will suddenly depend on a singleton. You’ll find that making one change will affect several unrelated parts of your project. Use singletons sparingly to save yourself this headache.

**The following happens when we use Global variables over Singletons**

- Unit testing requires clean isolated environments. This can become a nightmare when there’s a global mutable state hanging around as it will only tie up the states together. You’ll never be sure if the global state was changed in the previous unit test. So, you’ll have to set all globals to predetermined values before every test. That’s an overhead.

- Violates Encapsulation, encapsulation refers to restricting direct access to data.

- Keep global variables to a bare minimum in the worst-case scenario in order to avoid an unmanageable and chaotic codebase.

To shows off the advantages of Singletons, I created an example where we initialize a Singleton in `MainActivity.kt` and update a Singleton variable and then when we launch a new activity `FinalActivity.kt` with an intent we can use that updated Singleton variable. This is better than using global variables. 

In Kotlin, unlike Java, classes do not have static methods. In most cases, it’s recommended to simply use package-level functions instead. Since, Kotlin doesn’t have static member for class, it’s mean that you can’t create static method and static variable in Kotlin class, we need to use Singletons.

Our Singleton `Singleton.kt`, will looks like the following 

```kotlin
package com.example.singleton_kotlin_application

object Singleton {
    init {
        println("Singleton initialized")
    }

    var message = "Singletons rock"

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
    Singleton.message = "Singletons are cool"

    val test = Test()
}
```

Then our attempt of a static class in Kotlin `StaticClass.kt` will look like the following

```kotlin
package com.example.singleton_kotlin_application

class StaticClass(score: Int) {
    var score = 0
    var staticScore = 5

    init {
        this.score = score
    }

    fun getPoints(): Int {
        return staticScore
    }
}
```

To test the Singleton and the Static Class, we will use the following files

```kotlin
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
```

and

```kotlin
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
```

When we run the following code, we will notice our changes we make in our `Singleton` is carried over to the `FinalActivity.kt`, while our changes we made with our `Static class` are not, since static classes don't work in Kotlin, which means that we had to initialize the class again returning it back to its initial state.

We also want our `AndroidManifest.xml` to be something like the following

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.singleton_kotlin_application">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Singletonkotlinapplication">

        <activity android:name=".FinalActivity"
            android:parentActivityName=".MainActivity"/>

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

## How to run the project locally

To run the unit tests locally.

```gradle
./gradlew testdebugUnitTest
```

To run the ui tests locally, but first we need an emulator to be open.

```gradle
./gradlew connectedCheck
```

## Tools

**Linter:** we use the following linter [link](https://github.com/github/super-linter).

**Uploading Artifacts:**  we use the following way to upload Artifacts, they allow you to persist data like test results after a job has completed, see the following documentation [link](https://docs.github.com/en/actions/configuring-and-managing-workflows/persisting-workflow-data-using-artifacts).

**Creating images/icons:** we use Figma to create images and icon. Figma makes it very easy to create designs in many different formats.

**Creating a Mock Server:** we use a mock server with Postman to quickly test apis, to see how to create a mock server, see the following video [link](https://www.youtube.com/watch?v=rJY8uUH2TIk). 

**Converting JSON to Code:** A tool to generate models and serializers from JSON, schema, and GraphQL for working with data quickly & safely in any programming language [link](https://app.quicktype.io/).

### Mobile Specific Tools:
 
**Fastlane:** Fastlane allows us to automate our development and release process [link](https://docs.fastlane.tools/).

**App Center:** App Center is used to distribute an app, making it very easy to test on a physical device by using a fastlane plugin [link](https://github.com/microsoft/fastlane-plugin-appcenter).

**Proxyman:** we use Proxyman to view HTTP/HTTPS requests as they happen, it is easier to debug network connections on mobile on Proxyman where we can test and mock specific network responses, see the following documentation [link](https://docs.proxyman.io/debug-devices/ios-simulator). Proxyman also allows you intercept and edit requests/responses, see the following documentation [link](https://proxyman.io/blog/2019/08/Use-Breakpoint-to-intercept-and-edit-request-response-on-iOS-app.html). 

## Update Dependencies

**Npm:** How to update a npm package.
- [link](https://docs.npmjs.com/cli/update).

**Gemfile:** How to update a Gemfile package.
- [link](https://bundler.io/man/bundle-update.1.html#UPDATING-A-LIST-OF-GEMS).

## Documentations

**Git Squash:** How to Git Squash with VS Code [link](documentations/gitSquashDocument.md).

**Git Worktree:** How to use Git Worktree [link](documentations/gitWorktreeDocument.md).

**Git Empty Commit:** How to use Git Empty Commit [link](documentations/gitEmptyCommitDocument.md).

**Common Design Patterns and App Architectures for Mobile**: [link](https://www.raywenderlich.com/18409174-common-design-patterns-and-app-architectures-for-android#toc-anchor-001) and [link](https://dev.to/codalreef/learn-dependency-injection-with-doug-the-goldfish-3j43). 

## Releases

How to manage releases in a repository [link](https://help.github.com/en/github/administering-a-repository/managing-releases-in-a-repository). 

## Helpful resources

The following link provides a helpful tutorial on how to use the object keyword in Kotlin to define singleton, companion and anonymous objects and to ensure Java interoperability.
- [link](https://www.raywenderlich.com/23623842-object-in-kotlin-and-the-singleton-pattern).

The following link provides an example of using a static variable in Java, we would not want to repeat this in other projects due to possible issues.
- [link](https://github.com/JPrendy/android-card-game/blob/master/app/src/main/java/com/example/myapplication/Score.java).

The following links to a helpful video that explains `Singleton Pattern - Design Patterns`.
- [link](https://www.youtube.com/watch?v=sJ-c3BA-Ypo).

The following links to a helpful article explaining that `Kotlin does not have static variables` and we need to use Singletons classes instead.
- [link](https://medium.com/@waqarul/kotlin-static-member-fields-and-singletons-b79fd65aaf9b).

The following links to a helpful article explaining how Singletons work with Kotlin with included examples.
- [link](https://narbase.com/2020/06/30/design-patterns-singleton-with-kotlin-examples/).

The following links to a comment on how passing values between files with Singletons.
- [link](https://discuss.kotlinlang.org/t/pass-variable-from-file2-kt-to-file1-kt/14645/2).

The following links to a list of reason on why using global variables are bad.
- [link](http://wiki.c2.com/?GlobalVariablesAreBad).