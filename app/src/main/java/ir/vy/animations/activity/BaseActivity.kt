package ir.vy.animations.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import ir.vy.animations.R
import ir.vy.animations.util.autoPadding

open class BaseActivity : AppCompatActivity() {

    // on back pressed old way=>
//    @Deprecated("Deprecated in Java")
//    @SuppressLint("GestureBackNavigation")
//    override fun onBackPressed() {
//        super.onBackPressedDispatcher.onBackPressed()
//        overridePendingTransitionExit()
//    }

    // on back pressed New way =>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
                overridePendingTransitionExit()
            }
        })
    }

    // start Activity =>
    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        overridePendingTransitionEnter()
    }

    // finish =>
    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    //-----------------------------------------------
    // Enter =>
    private fun overridePendingTransitionEnter() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_OPEN,
                R.anim.slide_from_right,
                R.anim.slide_to_left
            )
        } else {
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
    }

    // Exit =>
    private fun overridePendingTransitionExit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_CLOSE,
                R.anim.slide_from_left,
                R.anim.slide_to_right
            )
        } else {
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }
}