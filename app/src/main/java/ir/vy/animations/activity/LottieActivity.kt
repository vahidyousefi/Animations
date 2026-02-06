package ir.vy.animations.activity

import android.animation.Animator
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import ir.vy.animations.databinding.ActivityLottieBinding

class LottieActivity : BaseActivity() {
    private lateinit var binding: ActivityLottieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLottieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        var count = 0.1f

        // Strat Animation
        binding.lottieAnim.playAnimation()

        binding.btnAnimation.setOnClickListener {
            // Progress
//            binding.lottieAnim.progress = count
//            count += 0.1f
//
//            if (count == 1f) {
//                count = 0f
//            }
        }

        // Create Listener for Animation
        binding.lottieAnim.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

            override fun onAnimationStart(animation: Animator) {
            }
        })
    }
}