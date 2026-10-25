package com.example.qrtrash

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.qrtrash.databinding.ActivityActivityBinding

class activity_activity : AppCompatActivity() {

    private lateinit var binding: ActivityActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Mengaktifkan Toolbar sesuai Modul 5
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Riwayat Laporan"
            subtitle = "Pantau status laporan Anda"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_back)
            val sizeInPx = (28 * resources.displayMetrics.density).toInt()
            val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, sizeInPx, sizeInPx, true)
            val backIcon = BitmapDrawable(resources, scaledBitmap)
            backIcon.setTint(Color.WHITE)

            setHomeAsUpIndicator(backIcon)
        }

        // 2. Menampilkan fragment1 secara default saat halaman pertama kali dibuka
        replaceFragment(fragment1(), addToBackStack = false)
        aturWarnaTombolAktif(1)

        // 3. Listener BackStack: Sinkronkan tombol atas saat tombol Back ditekan
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id)
            when (currentFragment) {
                is fragment1 -> aturWarnaTombolAktif(1)
                is fragment2 -> aturWarnaTombolAktif(2)
                is fragment3 -> aturWarnaTombolAktif(3)
            }
        }

        // 4. Event klik tombol untuk mengganti Fragment1, Fragment2, dan Fragment3
        binding.btnStatusPending.setOnClickListener {
            replaceFragment(fragment1())
            aturWarnaTombolAktif(1)
        }

        binding.btnStatusDiproses.setOnClickListener {
            replaceFragment(fragment2())
            aturWarnaTombolAktif(2)
        }

        binding.btnStatusSelesai.setOnClickListener {
            replaceFragment(fragment3())
            aturWarnaTombolAktif(3)
        }

        // 5. Navigasi Bottom Navigation
        binding.navBeranda.setOnClickListener {
            finish()
        }

        binding.navPeta.setOnClickListener {
            val intent = android.content.Intent(this, petatps::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Fungsi mengganti Fragment sesuai Modul Pertemuan 6 Tahap 4.4
    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        val transaction = supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

    // Fungsi mengatur warna tombol aktif
    private fun aturWarnaTombolAktif(nomorTombol: Int) {
        val warnaAktifBg = ColorStateList.valueOf(Color.parseColor("#22C55E"))
        val warnaNonAktifBg = ColorStateList.valueOf(Color.parseColor("#DCFCE7"))
        val teksPutih = Color.WHITE
        val teksHijauTua = Color.parseColor("#15803D")

        binding.btnStatusPending.backgroundTintList = if (nomorTombol == 1) warnaAktifBg else warnaNonAktifBg
        binding.btnStatusPending.setTextColor(if (nomorTombol == 1) teksPutih else teksHijauTua)

        binding.btnStatusDiproses.backgroundTintList = if (nomorTombol == 2) warnaAktifBg else warnaNonAktifBg
        binding.btnStatusDiproses.setTextColor(if (nomorTombol == 2) teksPutih else teksHijauTua)

        binding.btnStatusSelesai.backgroundTintList = if (nomorTombol == 3) warnaAktifBg else warnaNonAktifBg
        binding.btnStatusSelesai.setTextColor(if (nomorTombol == 3) teksPutih else teksHijauTua)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}