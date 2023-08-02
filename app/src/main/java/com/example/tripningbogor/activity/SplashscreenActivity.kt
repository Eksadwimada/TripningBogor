package com.example.tripningbogor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager

class SplashscreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //menghilangkan batas atas layar (status bar) sehingga aktivitas akan ditampilkan secara penuh layar.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        setContentView(com.example.tripningbogor.R.layout.activity_splashscreen)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
        // ketika aplikasi pertama kli dibuka dia menuju splashcreen dan akan delay
        // selama 2000s kmdn lgsg menuju halaman login

        //agar saat program dirun munculnya di splass page, kita hrs menyettingnya di manifest

    }
}