package com.example.tripningbogor.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Anotasi @Parcelize mengindikasikan bahwa kelas ModelTour akan diimplementasikan
// sebagai Parcelable secara otomatis oleh Kotlin Android Extensions
// mengirim data antar komponen, seperti antara Fragments atau Activities. ModelTour memiliki beberapa properti

@Parcelize
class ModelTour(
    var strImageWisata: Int,
    var strNamaWisata: String?,
    var strDeskripsi: String?,
    var strInfoWisata: String?,
    var latitude: Double,
    var longitude: Double
) : Parcelable

//Parcelable adalah untuk memudahkan proses pengiriman dan
// penerimaan objek antara komponen Android, terutama saat
// menggunakan Intent atau Bundle. Parcelable adalah salah satu cara
// untuk mengirimkan objek secara efisien antara Activity, Fragment,
// Service, atau komponen Android lainnya, dengan tujuan meminimalkan penggunaan memori dan overhead proses
