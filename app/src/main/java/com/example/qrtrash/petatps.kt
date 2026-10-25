package com.example.qrtrash

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.qrtrash.databinding.ActivityPetatpsBinding

class petatps : AppCompatActivity() {

    private lateinit var binding: ActivityPetatpsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetatpsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Mengaktifkan Toolbar dan Tombol Back sesuai Modul 5
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Peta TPS"
            subtitle = "Sebaran titik tempat sampah Pekanbaru"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)

            val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.ic_back)
            val sizeInPx = (28 * resources.displayMetrics.density).toInt()
            val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, sizeInPx, sizeInPx, true)
            val backIcon = BitmapDrawable(resources, scaledBitmap)
            backIcon.setTint(Color.WHITE)

            setHomeAsUpIndicator(backIcon)
        }

        // 2. Memuat Peta Pekanbaru dari URL menggunakan Glide (Modul 7 Tahap 4.6)
        // Ganti teks URL di bawah ini dengan URL peta Pekanbaru milikmu nanti
        val urlMapPekanbaru = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjHEn8tNdC9e5dUuSfayP8X7Zuvg3445VYuaz6oqbfntUdmiXqdQ874hq1iCAN4UlcNjinyR_qjNZgX6LuZ_-i4QYXu1Di1nrTx47YOqleyHkEmuKn008x8N_uvVkBfTqm_FFVo2m4qUjw/s1600/pekanbaru+kota.jpg"

        Glide.with(this)
            .load(urlMapPekanbaru)
            .into(binding.ivMapPekanbaru)

        // 3. Navigasi Menu Bawah: Klik Beranda -> kembali ke Dashboard
        binding.navBeranda.setOnClickListener {
            val intent = Intent(this, activity_main::class.java)
            startActivity(intent)
            finish()
        }

        // 4. Navigasi Menu Bawah: Klik Aktivitas -> pindah ke Halaman Aktivitas
        binding.navAktivitas.setOnClickListener {
            val intent = Intent(this, activity_activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Menangani klik tombol Back di Toolbar
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