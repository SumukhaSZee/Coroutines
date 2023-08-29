package com.example.coroutinebasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineBuilders : AppCompatActivity() {
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_builders)

        println("Main thread start")

        // Launch a coroutine using launch builder
        val job1 = GlobalScope.launch {
            println("Coroutine 1 started on ${Thread.currentThread().name}")
            delay(1000)
            println("Coroutine 1 finished on ${Thread.currentThread().name}")

            // This launch builder is known for fire and forget as it does not return anything//
        }

        // Launch a coroutine using async builder
        val deferredResult = GlobalScope.async {
            println("Coroutine 2 started on ${Thread.currentThread().name}")
            delay(1500)
            "Coroutine 2 result"

            //Async is a builder used to return something from the coroutine and also returns a deffered object as its result//
        }

        // Launch a blocking coroutine using runBlocking builder

        // runBlocking will block the current running thread to execute the coroutines defined within its block //
        // usually runBlocking is used for testing coroutines //
        runBlocking {
            println("Blocking Coroutine started on ${Thread.currentThread().name}")
            delay(500)
            println("Blocking Coroutine finished on ${Thread.currentThread().name}")
        }

        // Wait for the deferred result
        runBlocking {
            println("Awaiting async result: ${deferredResult.await()}")
            // We use await() method for retreiving the async coroutine result//
        }

        // Cancel the first coroutine
        job1.cancel()

        println("Main thread end")


    }
}

