package com.example.tripningbogor.activity

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tripningbogor.R
import com.example.tripningbogor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_home, R.id.navigation_profile) // Konfigurasi AppBar
        )
        // Menghubungkan ActionBar dengan NavController untuk mengelola perubahan judul dan tombol kembali.
        setupActionBarWithNavController(navController, appBarConfiguration)
        // Menghubungkan BottomNavigationView dengan NavController untuk mengelola navigasi antar tujuan (destination).
        navView.setupWithNavController(navController)
    }
}