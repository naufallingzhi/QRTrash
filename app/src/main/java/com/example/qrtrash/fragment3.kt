package com.example.qrtrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.qrtrash.databinding.FragmentFragment3Binding

class fragment3 : Fragment() {

    private var _binding: FragmentFragment3Binding? = null
    private val binding get() = _binding!!

    // Data laporan berstatus Selesai (Modul 7 Tahap 4.5)
    private val selesaiList = listOf(
        LaporanModel("TPS Jl. Diponegoro", "Tuntas: Sampah telah diangkut bersih", "Kemarin, 17:00 WIB", "Selesai", "https://avatar.iran.liara.run/public/16"),
        LaporanModel("TPS Jl. Pattimura Gobah", "Tuntas: Tong sampah rusak telah diganti", "2 hari lalu, 11:30 WIB", "Selesai", "https://avatar.iran.liara.run/public/17"),
        LaporanModel("TPS Pasar Kodim", "Tuntas: Pembersihan kontainer selesai", "3 hari lalu, 16:15 WIB", "Selesai", "https://avatar.iran.liara.run/public/18"),
        LaporanModel("TPS Jl. Harapan Raya", "Tuntas: Stiker QR Code baru terpasang", "4 hari lalu, 14:20 WIB", "Selesai", "https://avatar.iran.liara.run/public/19"),
        LaporanModel("TPS Jl. Garuda Sakti", "Tuntas: Pengangkutan ekstra selesai", "5 hari lalu, 10:00 WIB", "Selesai", "https://avatar.iran.liara.run/public/20"),
        LaporanModel("TPS Jl. Durian Sukajadi", "Tuntas: Area TPS sudah steril kembali", "Minggu lalu, 15:45 WIB", "Selesai", "https://avatar.iran.liara.run/public/21"),
        LaporanModel("TPS Jl. Yos Sudarso Rumbai", "Tuntas: Bak sampah telah diperbaiki", "Minggu lalu, 09:10 WIB", "Selesai", "https://avatar.iran.liara.run/public/22")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragment3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setNestedScrollingEnabled(binding.listLaporanItems, true)

        val adapter = LaporanAdapter(requireContext(), selesaiList)
        binding.listLaporanItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}