package com.example.tripningbogor.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tripningbogor.R
import com.example.tripningbogor.activity.DetailTourActivity
import com.example.tripningbogor.adapter.TourAdapter
import com.example.tripningbogor.databinding.FragmentHomeBinding
import com.example.tripningbogor.model.ModelTour
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT" //kunci untuk mengirim objek antar komponen
    }

    // deklarasi adapter
    private var adapter: TourAdapter? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    // Mendeklarasikan variabel untuk RecyclerView dan ArrayList yang akan menyimpan data ModelTour
    private lateinit var recyclerView: RecyclerView
    private lateinit var tourArrayList: ArrayList<ModelTour>

    //deklarasi untuk menyimpan sumber gambar
    lateinit var imageWisata: Array<Int>
    lateinit var namaWisata: Array<String>
    lateinit var descWisata: Array<String>
    lateinit var infoWisata: Array<String>
    lateinit var latWisata: Array<Double>
    lateinit var lonWisata: Array<Double>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mendapatkan referensi ke SearchView dari layout fragment_home.xml
        val searchView: SearchView = view.findViewById(R.id.searchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterTour(newText)
                return true
            }
        })

        dataInitialize() //inisialisasi data pada fragmen

        // Membuat objek LinearLayoutManager untuk mengatur tampilan daftar wisata dalam RecyclerView
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(com.example.tripningbogor.R.id.rvListWisata)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        // Jika adapter belum diinisialisasi
        if (adapter == null) {
            adapter = TourAdapter(tourArrayList) {
                val intent = Intent(context, DetailTourActivity::class.java)
                intent.putExtra(INTENT_PARCELABLE, it)
                startActivity(intent)
            }
        }
        recyclerView.adapter = adapter
    }

    // Fungsi untuk memfilter daftar wisata berdasarkan query pencarian yang diberikan
    private fun filterTour(query: String?) {
        val filteredTourList: ArrayList<ModelTour> = arrayListOf()

        if (query.isNullOrBlank()) {
            // Jika query kosong atau null, tampilkan seluruh daftar wisata
            filteredTourList.addAll(tourArrayList)
        } else {
            val searchQuery = query.toLowerCase()
            // Filter daftar wisata berdasarkan query
            for (tour in tourArrayList) {
                if (tour.strNamaWisata!!.toLowerCase().contains(searchQuery)) {
                    filteredTourList.add(tour)
                }
            }
        }

        // Update adapter dengan daftar wisata yang sudah difilter
        adapter?.updateData(filteredTourList)
    }

    private fun dataInitialize() {
        tourArrayList = arrayListOf<ModelTour>()

        // Inisialisasi array untuk menyimpan sumber gambar, judul, deskripsi, informasi, latitude, dan longitude objek wisata
        imageWisata = arrayOf(
            R.drawable.bukit,
            R.drawable.cibaliung,
            R.drawable.highland,
            R.drawable.pancar,
            R.drawable.img,
            R.drawable.taman,
        )

        namaWisata = arrayOf(
            getString(R.string.paralayang),
            getString(R.string.curug_cibaliung),
            getString(R.string.highland),
            getString(R.string.taman_wisata),
            getString(R.string.pemandian_air),
            getString(R.string.bunga),
        )

        descWisata = arrayOf(
            getString(R.string.desc1),
            getString(R.string.desc2),
            getString(R.string.desc3),
            getString(R.string.desc4),
            getString(R.string.desc5),
            getString(R.string.desc6),
        )

        infoWisata = arrayOf(
            getString(R.string.info1),
            getString(R.string.info2),
            getString(R.string.info3),
            getString(R.string.info4),
            getString(R.string.info5),
            getString(R.string.info6),
        )

        latWisata = arrayOf(
            -6.699990600560542,
            -6.598462526588329,
            -6.658486911763974,
            -6.58949202597003,
            -6.43135147708546,
            -6.727463913472105,
        )

        lonWisata = arrayOf(
            106.97293983950829,
            106.95810895493666,
            106.72923795493693,
            106.91177256684163,
            106.69584516657821,
            107.07942585493734,
        )

        //elemen digunakan utk objek yang di tambahkan ke tourarraylist
        for (i in imageWisata.indices) {

            val modelTour = ModelTour(imageWisata[i], namaWisata[i], descWisata[i], infoWisata[i], latWisata[i], lonWisata[i])
            tourArrayList.add(modelTour)
        }
    }
}
