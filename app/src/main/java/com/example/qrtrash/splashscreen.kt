package com.example.qrtrash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.qrtrash.databinding.ActivitySplashscreenBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class splashscreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menahan layar SplashScreen selama 2 detik menggunakan Coroutine (Modul 6 Tahap 3.4)
        lifecycleScope.launch {
            delay(2000)

            // Membaca status isLogin dari SharedPreferences
            val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
            val isLogin = sharedPref.getBoolean("isLogin", false)

            // Jika sudah login -> langsung ke Dashboard (activity_main)
            // Jika belum login -> ke halaman Login (MainActivity)
            val target = if (isLogin) {
                activity_main::class.java
            } else {
                MainActivity::class.java
            }

            startActivity(Intent(this@splashscreen, target))
            finish() // Menutup SplashScreen agar tidak kembali saat tombol back ditekan
        }
    }
}