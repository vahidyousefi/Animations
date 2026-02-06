package ir.vy.animations.threads

import android.util.Log

class Thread1 : Thread() {

    override fun run() {
        super.run()

        // Do Work
        Log.v("Thread", Thread.currentThread().name)
    }
}