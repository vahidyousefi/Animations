package ir.vy.animations

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import ir.vy.animations.databinding.SharedPreferencesBinding
import ir.vy.animations.util.autoPadding
import ir.vy.animations.util.showToast

class SharedPreferences : AppCompatActivity() {
    private lateinit var binding: SharedPreferencesBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SharedPreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()
        // auto padding from navigation system
        autoPadding(binding.root)

        // Show image Person
        Glide.with(this)
            .load(R.drawable.img_person)
            .transform(RoundedCorners(32))
            .into(binding.imgMain)

        //------------- Shared Preferences ------------

        sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)

        // put data into sharedPreferences
        binding.btnContinue.setOnClickListener {

            val userName = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            // way 1 for save and run SharedPreferences
            sharedPreferences.edit().putString("user", userName).apply()
            sharedPreferences.edit { putString("pass", password) }

            if (binding.radioMale.isChecked) {

                sharedPreferences.edit { putBoolean("isMale", true) }
            } else {

                sharedPreferences.edit { putBoolean("isMale", false) }
            }

            // way 2 > for save and run SharedPreferences
//            val editor = sharedPreferences
//            editor.edit { putString("name", userName) }
//            editor.edit { putString("pass", password) }
            showToast(this, "data saved")
        }

        // get from sharedPreferences
        val editor = sharedPreferences

        val user = editor.getString("user", "")
        val pass = editor.getString("pass", "")
        val isMale = editor.getBoolean("isMale", true)

        binding.edtUsername.setText(user)
        binding.edtPassword.setText(pass)

        if (isMale) {
            binding.radioMale.isChecked = true
        } else {
            binding.radioFemale.isChecked = true
        }

        // Check exist item
        editor.contains("x")
        // Remove an item
        editor.edit { remove("x") }
        // delete all data in SharedPreferences
//        editor.edit { clear() }

        //----------- First Run -------------

        // get sharedPreferences
        val sharedPrefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE)
        val firstRun = sharedPrefs.getBoolean("isFirstRun", true)

        // Check first run
        if (firstRun) {
            showToast(this, "first run ...")
            // save flag for next run
            sharedPrefs.edit {
                putBoolean("isFirstRun", false)
                apply()
            }
        }
    }
}