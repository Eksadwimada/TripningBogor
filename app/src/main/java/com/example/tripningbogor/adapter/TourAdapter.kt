package com.example.tripningbogor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tripningbogor.R
import com.example.tripningbogor.model.ModelTour

// untuk menghubungkan data dari model objek wisata ke dalam RecyclerView
class TourAdapter (private var tourList: ArrayList<ModelTour>, val listener: (ModelTour) -> Unit)
    : RecyclerView.Adapter<TourAdapter.TourViewHolder>() {

    // Fungsi untuk membuat ViewHolder baru untuk setiap item di RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TourViewHolder {
        // Menginisialisasi tata letak item_wisata.xml
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_wisata, parent, false)
        return TourViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TourViewHolder, position: Int) {
        holder.bindView(tourList[position], listener)

        // Mendapatkan objek ModelTour dari daftar
        val currentItem = tourList[position]
        holder.strImageWisata.setImageResource(currentItem.strImageWisata)
        holder.strNamaWisata.text = currentItem.strNamaWisata
        itemCount
    }

    // Fungsi untuk mendapatkan jumlah item dalam daftar
    override fun getItemCount(): Int {
        return tourList.size
    }

    class TourViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val strImageWisata: ImageView = itemView.findViewById(R.id.imageWisata)
        val strNamaWisata: TextView = itemView.findViewById(R.id.tvNamaWisata)

        // Fungsi untuk menghubungkan data ModelTour dengan ViewHolder dan menetapkan listener klik pada item
        fun bindView(ModelTour: ModelTour, listener: (ModelTour) -> Unit) {
            strImageWisata.setImageResource(ModelTour.strImageWisata)
            strNamaWisata.text = ModelTour.strNamaWisata
            itemView.setOnClickListener {
                listener(ModelTour)
            }
        }
    }

    fun updateData(newTourList: ArrayList<ModelTour>) {
        tourList = newTourList
        notifyDataSetChanged()
    }
}