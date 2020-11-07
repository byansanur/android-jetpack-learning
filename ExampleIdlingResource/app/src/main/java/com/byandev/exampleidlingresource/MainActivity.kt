package com.byandev.exampleidlingresource

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.test.espresso.IdlingResource

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var text_view: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        text_view = findViewById(R.id.text_view)

        button.setOnClickListener {
            delay1()
            delay2()
        }

    }


    private fun delay1() {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            text_view.text = getString(R.string.delay1)
            decrement()

        }, 2000)
    }

    private fun delay2() {
        EspressoIdlingResource.increment()
        Handler().postDelayed({
            text_view.text = getString(R.string.delay2)
            decrement()
        }, 3000)
    }

    private fun decrement() {
        if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
            // memberitahu bahwa tugas sudah selesai dijalankan
            EspressoIdlingResource.decrement()
        }
    }
}