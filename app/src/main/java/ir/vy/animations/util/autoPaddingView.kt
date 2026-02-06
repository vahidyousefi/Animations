package ir.vy.animations.util

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

fun autoPadding(rootViewGroup: View) {

    ViewCompat.setOnApplyWindowInsetsListener(rootViewGroup) { view, insets ->
        val bottom = insets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom
        (view.layoutParams as? ViewGroup.MarginLayoutParams)?.let { params ->
            params.bottomMargin = bottom
            view.layoutParams = params
        }
        insets
    }
}