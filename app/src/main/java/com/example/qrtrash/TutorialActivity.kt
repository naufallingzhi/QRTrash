package com.example.qrtrash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Daftar 3 Fragment Tutorial QRTrash (Modul 9 Tahap 5.3)
        val fragmentsList = listOf(
            Tutorial1Fragment(),
            Tutorial2Fragment(),
            Tutorial3Fragment()
        )

        // 2. Pasang Adapter ke ViewPager2 dan hubungkan DotsIndicator (Modul 9 Tahap 6.3)
        val adapter = TutorialFragmentAdapter(this, fragmentsList)
        binding.tutorialViewPager.adapter = adapter
        binding.dotIndicator.attachTo(binding.tutorialViewPager)

        // 3. Cek status login untuk menentukan tujuan tombol selesai
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val isLogin = sharedPref.getBoolean("isLogin", false)

        if (isLogin) {
            binding.btnCloseTutorial.text = "Mengerti & Kembali"
        } else {
            binding.btnCloseTutorial.text = "Lanjut ke Login"
        }

        // 4. Aksi klik tombol di bagian bawah Tutorial
        binding.btnCloseTutorial.setOnClickListener {
            if (isLogin) {
                // Jika dibuka dari menu Layanan QRTrash di Beranda, cukup tutup halaman ini
                finish()
            } else {
                // Jika dibuka setelah Splash Screen (belum login), lanjut ke halaman Login (MainActivity)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}