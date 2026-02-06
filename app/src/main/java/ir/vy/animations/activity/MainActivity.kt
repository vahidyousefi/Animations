package ir.vy.animations.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import android.view.animation.TranslateAnimation
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ir.vy.animations.R
import ir.vy.animations.SharedPreferences
import ir.vy.animations.databinding.ActivityMainBinding
import ir.vy.animations.util.showToast
import ir.vy.animations.threads.Thread1
import ir.vy.animations.threads.Thread2
import ir.vy.animations.util.autoPadding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // auto padding from navigation system
        autoPadding(binding.root)

        buttonsListener()
    }
    
    // Listeners
    private fun buttonsListener() {

        // go to activity Shared Preference
        binding.btnGoToShPre.playAnimation()
        binding.btnGoToShPre.setOnClickListener {
            val sharedPre = Intent(this, SharedPreferences::class.java)
            startActivity(sharedPre)
        }
        //-------------------------------------------------------
        // go to activity Lottie
        binding.btnGoToLottie.playAnimation()
        binding.btnGoToLottie.setOnClickListener {

            val intent = Intent(this, LottieActivity::class.java)
            startActivity(intent)
        }

        // DropDownMenu
        val animations =
            listOf(
                "Alpha",
                "Scale",
                "Translate",
                "Rotate",
                "Multiple",
                "Alpha from XML"
            )
        val adapterAnim = ArrayAdapter(this, R.layout.item_text, animations)
        (binding.edtAnim.editText as AutoCompleteTextView).setAdapter(adapterAnim)

        binding.btnAnim.playAnimation()
        binding.btnAnim.setOnClickListener {

            // get input from dropDownMenu
            val result = (binding.edtAnim.editText as AutoCompleteTextView).text.toString()
            when (result) {

                "Alpha" -> {
                    showToast(this, "Alpha Animation Started")
                    alphaAnimation()
                }

                "Scale" -> {
                    showToast(this, "Scale Animation Started")
                    scaleAnimation()
                }

                "Translate" -> {
                    showToast(this, "Translate Animation Started")
                    translateAnimation()
                }

                "Rotate" -> {
                    showToast(this, "Rotate Animation Started")
                    rotateAnimation()
                }

                "Multiple" -> {
                    showToast(this, "Multiple Animation Started")
                    useMultipleAnimation()
                }

                "Alpha from XML" -> {
                    showToast(this, "Alpha Animation from Xml Started")
                    useAnimFromXml()
                }

                else -> {
                    showToast(this, "Please select a valid animation")
                }
            }
            // Clear text in TextInputLayout after show Animation (Worked for all fun anim)
//            binding.edtAnim.setText("")

            // Clear text in TextInputLayout and reset focus (after chose user item in TextInputLayout and started)
//            (binding.edtAnim.editText as AutoCompleteTextView).setText("")
            (binding.edtAnim.editText as AutoCompleteTextView).apply {
                setText("")
                clearFocus()
            }
        }

        // Reset hint focus when user clicked But did not select anything
        (binding.edtAnim.editText as AutoCompleteTextView).setOnDismissListener {
            val autoCompleteTextView = binding.edtAnim.editText as AutoCompleteTextView
            if (autoCompleteTextView.text.isEmpty()) {
                autoCompleteTextView.setText("")
                autoCompleteTextView.clearFocus()
            }
        }
    }

    // Alpha Animation ( fading )
    private fun alphaAnimation() {

        val anim = AlphaAnimation(1f, 0f)

        //---------- working all fun for all Animation Type ------------
        // time show animation
        anim.duration = 1000

        // don`t go back state
        anim.fillAfter = true

        // repeat Animation
        anim.repeatCount = 3
        // repeatMode Animation
        anim.repeatMode = Animation.REVERSE

        // Animation Listener
        anim.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }
        })
        //-------------------------------------------------------------------

        binding.imageAnim.startAnimation(anim)
    }

    // Scale Animation ( scaling or resize )
    private fun scaleAnimation(): ScaleAnimation {

        // simple mode
        val anim = ScaleAnimation(1f, 2f, 1f, 2f)
        anim.duration = 2000
        anim.fillAfter = true

        // advanced mode (recommended)
        val anim2 = ScaleAnimation(
            1f, 1.85f,
            1f, 1.85f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        anim2.duration = 2000
        anim2.fillAfter = true
        anim2.repeatCount = 3
        anim2.repeatMode = Animation.REVERSE

        binding.imageAnim.startAnimation(anim2)

        //for multipleAnimation > scale
        return anim2
    }

    // Translate Animation ( Moving )
    private fun translateAnimation() {

        val transAnim = TranslateAnimation(0f, 0f, -130f, 370f)

        transAnim.duration = 2000
        transAnim.fillAfter = true
//        transAnim.repeatCount = 3
        transAnim.repeatMode = Animation.REVERSE

        /** Interpolator :
         * 1 - Accelerate ( Low speed to High Speed )
         * 2 - Decelerate ( High speed to Low speed )
         * 3 - AccelerateDecelerate ( First Low Speed, Next fast Speed, Last low Speed )
         * 4 - Linear ( Speed same )
         * 5 - Bounce ( similar to the Ball-Falling )
         * 6 - Overshoot ( move like a Spring )
         * 7 - Anticipate ( Come Back then Go )
         * 8 - AnticipateOvershoot ( mix from Anticipate and Overshoot )
         * 9 - cycle (Go back Loop ping-pong only input 0f to 1f  )
         */
        transAnim.interpolator = BounceInterpolator()

        binding.imageAnim.startAnimation(transAnim)
    }

    // Rotate Animation
    private fun rotateAnimation(): RotateAnimation {

        // simple mode
        val rotateAnim1 = RotateAnimation(0f, 360f)
        rotateAnim1.duration = 2000
        rotateAnim1.fillAfter = true
        //----------------------------------------

        // Advanced mode
        val rotateAnim2 = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnim2.duration = 2000
        rotateAnim2.fillAfter = true
        rotateAnim2.repeatCount = 3

        binding.imageAnim.startAnimation(rotateAnim2)

        //for multipleAnimation > Rotate
        return rotateAnim2
    }

    // MultipleAnimation
    private fun useMultipleAnimation() {

        val animSet = AnimationSet(true)

        animSet.addAnimation(scaleAnimation())
        animSet.addAnimation(rotateAnimation())

        binding.imageAnim.startAnimation(animSet)
    }

    // use Animation from xml
    private fun useAnimFromXml() {

        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha_trans)
        anim.fillAfter = true

        binding.imageAnim.startAnimation(anim)
    }
}