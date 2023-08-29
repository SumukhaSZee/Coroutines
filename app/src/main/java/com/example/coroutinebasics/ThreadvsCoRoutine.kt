
package com.example.coroutinebasics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class ThreadvsCoRoutine : AppCompatActivity() {
    private var TAG: String = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val time1 = measureTimeMillis {  // printing something in thread and measuring time taken //
            repeat(10000) {

                thread {
                    print("Hello from Thread");
                }


            }
        }
        println("Time Taken by Thread : ${time1 / 1000}");

        var time2 :Long = 0L
        runBlocking {
             time2 = measureTimeMillis {  // printing something in Coroutine and measuring time taken //
                    repeat(10000) {

                        launch {
                            print("Hello from Coroutine");
                        }


                    }
                }

        }
        println("Time taken By Coroutine : ${time2/1000}")

        //This proves that coroutines are faster when compared to threads and are lightweight in nature//
    }
}




