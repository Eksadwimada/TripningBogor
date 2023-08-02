package com.example.tripningbogor.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.tripningbogor.R
import androidx.appcompat.app.AppCompatActivity
import com.example.tripningbogor.model.ModelTour
import com.example.tripningbogor.ui.HomeFragment

class DetailTourActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tour)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)//tombol kembali

        //data modeltour dari intent yg dikirim dari HomeFragment
        val Tour = intent.getParcelableExtra<ModelTour>(HomeFragment.INTENT_PARCELABLE)
        //memeriksa data
        if (Tour != null) {
            val tour = Tour

            // Mendapatkan referensi ImageView, TextView, dan Button dari layout activity_detail_tour.xml.
            val imageTour = findViewById<ImageView>(R.id.imageWisata)
            val titleTour = findViewById<TextView>(R.id.tvNamaWisata)
            val descTour = findViewById<TextView>(R.id.tvDeskripsi)
            val infoTour = findViewById<TextView>(R.id.tvInfoWisata)

            //mengatur setiap variabel dari objek
            imageTour.setImageResource(Tour?.strImageWisata!!)
            titleTour.text = Tour.strNamaWisata
            descTour.text = Tour.strDeskripsi
            infoTour.text = Tour.strInfoWisata

            val buttonShowMap: Button = findViewById(R.id.btnOpenMaps)
            buttonShowMap.setOnClickListener {
                // Mengarahkan user ke activity peta wisata (TourMapActivity)
                val intent = Intent(this, TourMapActivity::class.java)
                // Kirim data lokasi wisata (latitude dan longitude) ke activity peta wisata
                intent.putExtra("latitude", tour.latitude)
                intent.putExtra("longitude", tour.longitude)
                startActivity(intent)
            }
        } else {
            // Jika data Tour null, maka akhiri activity saat ini
            finish()
        }
    }

    //Fungsi onSupportNavigateUp() dipanggil saat tombol kembali di ActionBar ditekan
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
        }
}





