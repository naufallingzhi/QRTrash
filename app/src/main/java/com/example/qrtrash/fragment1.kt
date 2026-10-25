package com.example.qrtrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.qrtrash.databinding.FragmentFragment1Binding

class fragment1 : Fragment() {

    private var _binding: FragmentFragment1Binding? = null
    private val binding get() = _binding!!

    // Data laporan berstatus Pending (Modul 7 Tahap 4.5)
    private val pendingList = listOf(
        LaporanModel("TPS Pasar Pagi Arengka", "Kendala: Tempat Sampah Penuh", "Hari ini, 10:24 WIB", "Pending", "https://avatar.iran.liara.run/public/1"),
        LaporanModel("TPS Jl. HR Soebrantas Panam", "Kendala: Kontainer Meluber", "Hari ini, 09:40 WIB", "Pending", "https://avatar.iran.liara.run/public/2"),
        LaporanModel("TPS Taman Kota Pekanbaru", "Kendala: Tutup Tong Rusak", "Hari ini, 08:15 WIB", "Pending", "https://avatar.iran.liara.run/public/3"),
        LaporanModel("TPS Jl. Riau Ujung", "Kendala: Tumpukan Sampah Liar", "Kemarin, 17:30 WIB", "Pending", "https://avatar.iran.liara.run/public/4"),
        LaporanModel("TPS Pasar Cik Puan", "Kendala: Bau Menyengat & Penuh", "Kemarin, 16:10 WIB", "Pending", "https://avatar.iran.liara.run/public/5"),
        LaporanModel("TPS Jl. Paus Marpoyan", "Kendala: Bak Sampah Retak", "Kemarin, 14:05 WIB", "Pending", "https://avatar.iran.liara.run/public/6"),
        LaporanModel("TPS Jl. Kaharuddin Nasution", "Kendala: Tempat Sampah Penuh", "Kemarin, 11:20 WIB", "Pending", "https://avatar.iran.liara.run/public/7"),
        LaporanModel("TPS Simpang Tiga Bandara", "Kendala: QR Code Mulai Pudar", "2 hari lalu, 15:45 WIB", "Pending", "https://avatar.iran.liara.run/public/8")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengaktifkan nested scrolling agar CoordinatorLayout Toolbar bisa ikut tergulung
        ViewCompat.setNestedScrollingEnabled(binding.listLaporanItems, true)

        // Pasang Custom Adapter ke ListView (Modul 7 Tahap 4.7)
        val adapter = LaporanAdapter(requireContext(), pendingList)
        binding.listLaporanItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}