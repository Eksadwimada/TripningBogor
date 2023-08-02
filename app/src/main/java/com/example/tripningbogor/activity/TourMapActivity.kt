package com.example.tripningbogor.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class TourMapActivity : AppCompatActivity(), OnMapReadyCallback {

    // Variabel untuk menyimpan data latitude dan longitude lokasi wisata
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.tripningbogor.R.layout.activity_tour_map)

        // Mendapatkan data latitude dan longitude lokasi wisata dari intent yang dikirim dari activity sebelumnya (DetailTourActivity)
        latitude = intent.getDoubleExtra("latitude", 0.0)
        longitude = intent.getDoubleExtra("longitude", 0.0)

        // Inisialisasi fragment peta
        val mapFragment = supportFragmentManager.findFragmentById(com.example.tripningbogor.R.id.mapView) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        // Menambahkan marker ke lokasi wisata
        val location = LatLng(latitude, longitude)
        googleMap.addMarker(MarkerOptions().position(location).title("Lokasi Wisata"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}