package com.example.qrtrash

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.qrtrash.databinding.ActivityJadwalTrukBinding
import com.google.android.material.tabs.TabLayoutMediator

class JadwalTrukActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJadwalTrukBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJadwalTrukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Setup Toolbar dan Tombol Back
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Jadwal Truk DLHK"
            subtitle = "Jam operasional pengangkutan sampah"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_back)
            val sizeInPx = (28 * resources.displayMetrics.density).toInt()
            val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, sizeInPx, sizeInPx, true)
            val backIcon = BitmapDrawable(resources, scaledBitmap)
            backIcon.setTint(Color.WHITE)

            setHomeAsUpIndicator(backIcon)
        }

        // 2. Inisialisasi dan pasang Adapter ke ViewPager2 (Modul 10 Tahap 1.4)
        val tabsAdapter = JadwalTabsAdapter(this)
        binding.viewPager.adapter = tabsAdapter

        // 3. Hubungkan TabLayout dan ViewPager2 + Pasang Ikon & Badge (Modul 10 Tahap 1.4 & 2.1)
        val namaHari = listOf("Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu")

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = namaHari[position]
            tab.icon = ContextCompat.getDrawable(this, R.drawable.ic_clock)

            when (position) {
                // Senin s.d. Jumat: Badge dengan angka 3 (3 Shift Utama)
                0, 1, 2, 3, 4 -> {
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                    badge.number = 3
                }
                // Sabtu & Minggu: Badge titik tanpa angka (Jadwal Akhir Pekan)
                5, 6 -> {
                    val badge = tab.getOrCreateBadge()
                    badge.isVisible = true
                }
            }
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}