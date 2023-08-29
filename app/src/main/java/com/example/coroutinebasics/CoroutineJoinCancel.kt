package com.example.coroutinebasics

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main(){

    var TAG = "Main"
    val job = CoroutineScope(Dispatchers.IO).launch {
        repeat(5){
            delay(1000)
            Log.d(TAG,"Coroutine is Running")
        }
    }

    val job2 = CoroutineScope(Dispatchers.IO).launch {
        Log.d(TAG,"Starting a long running task")
        withTimeout(3000){ // used to cancel a coroutine automatically after a specifed time //
            for(i in 40..100L){
                /*if(isActive)*/        // Checks whether coroutine is active or not //
                Log.d(TAG,"i: ${i} and i*i = ${task(i)}")
            }

        }

        Log.d(TAG,"Ending a long running task")
    }

    runBlocking {
        delay(1000) //Delay the main Thread
        //job.join()  // Join will cause the main thread to wait until coroutine completes its job//
        job.cancel()  // Used to  manually Cancel a coroutine//
        Log.d(TAG,"Cancelled Job")
        Log.d(TAG,"Main Thread continues")
    }
}

fun task(a:Long):Long{
    var res = a*a
    return res

}