package com.example.tripningbogor.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tripningbogor.activity.LoginActivity
import com.example.tripningbogor.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    lateinit var auth: FirebaseAuth

    private val binding get() = _binding!!

    override fun onCreateView (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        // Mendapatkan data pengguna yang sedang login
        val user = auth.currentUser

        // Jika pengguna telah login (user tidak null), maka tampilkan nama dan email pengguna di tampilan
        if (user != null) {
            binding.edtName.setText(user.displayName)
            binding.edtEmail.setText(user.email)
        }

        binding.btnLogout.setOnClickListener {
            buttonOut()
        }
    }

    private fun buttonOut() {
        auth = FirebaseAuth.getInstance()
        auth.signOut()
        val i = Intent(context, LoginActivity::class.java)
        startActivity(i)
        activity?.finish()
    }

}