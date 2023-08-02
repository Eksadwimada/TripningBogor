package com.example.tripningbogor.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tripningbogor.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding //Deklarasi variabel binding penghubungan ui
    private lateinit var firebaseAuth: FirebaseAuth //Deklarasi variabel firebase untuk mengelola otentikasi pengguna firebase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater) //inisialisasi variabel binding dengan memanggil metode inflate
        setContentView(binding.root)
        supportActionBar?.hide()

        firebaseAuth = FirebaseAuth.getInstance() //mendapatkan instance dari firebase dan inisialisasi

        //listener pada objek ketika di klik
        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent) //memulai aktivitas baru
        }

        binding.btnRegister.setOnClickListener {
            val email = binding.emailEt.text.toString() //mengambil teks pada objek emailEt, lalu jadi string kemudian nilai disimpan di variabel email
            val pass = binding.passET.text.toString()

            //memeriksa variabel jika kosong
            if (email.isEmpty()) {
                binding.emailEt.error = "Email Harus Diisi!"
                binding.emailEt.requestFocus()
                return@setOnClickListener //Menghentikan eksekusi fungsi setOnClickListener
            }

            //memeriksa apa nilai email tidak sesuai dengan pola alamat email yang valid
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.emailEt.error = "Email Tidak Valid"
                binding.emailEt.requestFocus()
                return@setOnClickListener
            }

            if (pass.isEmpty()) {
                binding.passET.error = "Password Harus Diisi!"
                binding.passET.requestFocus()
                return@setOnClickListener
            }

            //memeriksa apa panjang nilai < 5 karakter
            if (pass.length < 5) {
                binding.passET.error = "Password Minimal 5 Karakter"
                binding.passET.requestFocus()
                return@setOnClickListener
            }

            dataUserFirebase(email, pass) //memanggil fungsi dengan memanggil parameter <- untuk mendaftarkan pengguna baru
        }
    }

    //fungsi parameter
    //digunakan untuk operasi pendaftaran pengguna
    private fun dataUserFirebase(email: String, pass: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Berhasil Membuat Akun", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}