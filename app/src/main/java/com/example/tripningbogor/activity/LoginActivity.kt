package com.example.tripningbogor.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tripningbogor.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding //Deklarasi variabel binding penghubungan ui
    private lateinit var firebaseAuth: FirebaseAuth //Deklarasi variabel firebase untuk mengelola otentikasi pengguna firebase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide() //menyembunyikan action bar

        firebaseAuth = FirebaseAuth.getInstance() // Mendapatkan instance FirebaseAuth dari FirebaseAuthentication

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish() // Menyelesaikan activity saat ini jadi ketika kembali dari RegisterActivity, LoginActivity akan ditutup.
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString() // Mengambil email dan password yang dimasukkan
            val pass = binding.passET.text.toString()

            //jika keduanya tidak kosong, maka...
            if (email.isNotEmpty() && pass.isNotEmpty()) {

                //metode untuk melakukan proses masuk dengan nilai yang diberikan
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent (this, MainActivity::class.java)
                        startActivity(intent) //memulai MainActivity
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Masih kosong nih !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}