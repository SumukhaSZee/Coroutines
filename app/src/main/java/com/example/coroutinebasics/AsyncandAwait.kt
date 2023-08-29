package com.example.coroutinebasics

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis


private var TAG = "MainActivity"
class AsyncandAwait : AppCompatActivity() {
    private var TAG = "MainActivity"
    lateinit var text:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)


        text = findViewById(R.id.textView3)
    val job1 = CoroutineScope(Dispatchers.IO).launch {
        val time = measureTimeMillis {      // Sequential execution without async
           /* val res1 = apicall1()
            val res2 = apicall2()*/
            val res1 = async { apicall1() }     // Async Execution launching seperate coroutines for each task //
            val res2 = async { apicall2() }
            Log.d(TAG,"Response from api1 : ${res1.await()}");
            Log.d(TAG,"Response from api2 : ${res2.await()}");
        }

        Log.d(TAG,"Total Time Taken : ${time}")



    }

     val job2 = CoroutineScope(Dispatchers.IO).launch {
         Log.d(TAG,"This Coroutine will obtain the apiresponse in IOThread and switches to Main Thread to" +
                 "Display those changes");
         var response = apicall1();
         withContext(Dispatchers.Main){
             text.setText(response)
         }
     }


}

suspend fun apicall1():String{
    delay(3000);
    return "apiresponse1"
}

suspend fun apicall2():String{
    delay(3000);
    return "apiresponse2"
}

}
