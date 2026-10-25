package com.example.qrtrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.example.qrtrash.databinding.FragmentFragment2Binding

class fragment2 : Fragment() {

    private var _binding: FragmentFragment2Binding? = null
    private val binding get() = _binding!!

    // Data laporan berstatus Diterima (Modul 7 Tahap 4.5)
    private val diterimaList = listOf(
        LaporanModel("TPS Jl. Jenderal Sudirman", "Petugas DLHK sedang menuju lokasi", "Hari ini, 09:00 WIB", "Diterima", "https://avatar.iran.liara.run/public/9"),
        LaporanModel("TPS Jl. Tuanku Tambusai", "Armada truk pengangkut dijadwalkan", "Kemarin, 15:20 WIB", "Diterima", "https://avatar.iran.liara.run/public/10"),
        LaporanModel("TPS Stadion Utama Riau", "Verifikasi perbaikan fasilitas disetujui", "Kemarin, 13:00 WIB", "Diterima", "https://avatar.iran.liara.run/public/11"),
        LaporanModel("TPS Jl. Arifin Ahmad", "Menunggu giliran angkut sore ini", "Kemarin, 10:15 WIB", "Diterima", "https://avatar.iran.liara.run/public/12"),
        LaporanModel("TPS Pasar Bawah", "Tim kebersihan sedang mengangkut", "2 hari lalu, 16:40 WIB", "Diterima", "https://avatar.iran.liara.run/public/13"),
        LaporanModel("TPS Jl. Umban Sari Rumbai", "Penggantian tong sampah diproses", "2 hari lalu, 14:10 WIB", "Diterima", "https://avatar.iran.liara.run/public/14"),
        LaporanModel("TPS Jl. Soekarno Hatta", "Petugas wilayah sudah menerima tiket", "3 hari lalu, 09:30 WIB", "Diterima", "https://avatar.iran.liara.run/public/15")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setNestedScrollingEnabled(binding.listLaporanItems, true)

        val adapter = LaporanAdapter(requireContext(), diterimaList)
        binding.listLaporanItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}