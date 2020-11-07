package com.byandev.exampleidlingresource

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    // increment, decrement dan getEspressoIdlingResource. Increment digunakan untuk menambahkan state loading dan decrement untuk mengurangi state loading-nya. Seperti contoh pada kode delay:
    private val RESOURCE: String? = "GLOBAL"
    private val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }

    fun getEspressoIdlingResource() : IdlingResource {
        return espressoTestIdlingResource
    }
}