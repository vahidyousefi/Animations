package ir.vy.animations.threads

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainThread : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //--------------------   THREADS   -------------------------------
        Log.v("Thread in main Activity", Thread.currentThread().name)

        // Method 1
        val thread1 = Thread1()
        thread1.start()

        // Method 2
        val thread2 = Thread(Thread2())
        thread2.start()

        // Method 3
        val thread3 = Thread(object : Runnable {
            override fun run() {
                Log.v("Thread in main Activity(M3)", Thread.currentThread().name)
            }
        })
        thread3.start()

        // Method 4 ( easy way )
        val thread4 = Thread {
            Log.v("Thread in main Activity(M4)", Thread.currentThread().name)
        }.start()
    }
}