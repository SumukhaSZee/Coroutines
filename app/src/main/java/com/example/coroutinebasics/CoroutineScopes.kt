package com.example.coroutinebasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout.TabGravity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CoroutineScopes : AppCompatActivity() {
    private var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        //This code tells us how to use different Scopes of Coroutines//

        val job1 = GlobalScope.launch {
            // this is used to create coroutines that exist throughout the lifecycle of an android application//
            delay(1000)
            Log.d(TAG,"GlobalScope is running");
        }

        val job2 = lifecycleScope.launch {
            //This is used to create coroutines that exist as long as the lifecycle of the component in which it exists is alive//
            delay(2000)
            Log.d(TAG,"LifeCycleScope is Running")
            //If we destroy the activity then this coroutine stops automatically//
        }

        runBlocking {
            coroutineScope {
                Log.d(TAG,"CoroutineScope is Running");  // CoroutineScope exists until the life cycle of the component in which it is defined//
            }
            job1.join()
            job2.join()
        }






    }
}