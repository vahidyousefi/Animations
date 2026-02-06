package ir.vy.animations.threads

import android.util.Log

class Thread2 : Runnable {

    override fun run() {
        // Do Work
        Log.v("Thread", Thread.currentThread().name)
    }
}