package com.example.qrtrash

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class JadwalTabsAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {

    // Jumlah total tab hari (Senin sampai Minggu = 7 tab)
    override fun getItemCount(): Int = 7

    // Menentukan Fragment berdasarkan posisi tab (Modul 10 Tahap 1.3)
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> JadwalHariFragment(0) // Senin
            1 -> JadwalHariFragment(1) // Selasa
            2 -> JadwalHariFragment(2) // Rabu
            3 -> JadwalHariFragment(3) // Kamis
            4 -> JadwalHariFragment(4) // Jumat
            5 -> JadwalHariFragment(5) // Sabtu
            6 -> JadwalHariFragment(6) // Minggu
            else -> throw IllegalStateException("Posisi tidak valid")
        }
    }
}