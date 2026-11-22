package com.example.qrtrash

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityMainBinding
import com.google.android.material.button.MaterialButton

class activity_main : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Ambil username dari SharedPreferences
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Naufal Lingzhi")
        binding.tvGreeting.text = "Hallo, $username"

        // 2. Tampilkan Pop-up Selamat Datang jika baru saja berhasil Login
        val showWelcome = intent.getBooleanExtra("SHOW_WELCOME_POPUP", false)
        if (showWelcome) {
            AlertDialog.Builder(this)
                .setTitle("Login Berhasil ~")
                .setMessage("Selamat datang kembali, $username!\nMari bersama wujudkan Pekanbaru Bersih.")
                .setPositiveButton("Mulai") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            intent.removeExtra("SHOW_WELCOME_POPUP")
        }

        // 3. Aksi klik 5 Tombol Layanan QRTrash dengan Efek Aktif + 1 Tombol Tutorial (Modul 9)
        binding.btnGrid1.setOnClickListener {
            aturMenuLayananAktif(binding.btnGrid1)
            Toast.makeText(this, "Layanan Aktif: Lapor TPS Penuh", Toast.LENGTH_SHORT).show()
        }
        binding.btnGrid2.setOnClickListener {
            aturMenuLayananAktif(binding.btnGrid2)
            Toast.makeText(this, "Layanan Aktif: Lapor Bak Sampah Rusak", Toast.LENGTH_SHORT).show()
        }
        binding.btnGrid3.setOnClickListener {
            aturMenuLayananAktif(binding.btnGrid3)
            Toast.makeText(this, "Layanan Aktif: Lapor Tumpukan Sampah Liar", Toast.LENGTH_SHORT).show()
        }
        binding.btnGrid4.setOnClickListener {
            aturMenuLayananAktif(binding.btnGrid4)
            startActivity(Intent(this, JadwalTrukActivity::class.java))
        }
        binding.btnGrid5.setOnClickListener {
            aturMenuLayananAktif(binding.btnGrid5)
            Toast.makeText(this, "Info Aktif: Menampilkan titik Bank Sampah Pekanbaru", Toast.LENGTH_SHORT).show()
        }

        // Tombol ke-6 di GridLayout tetap membuka halaman Tutorial Screen
        binding.btnGridTutorial.setOnClickListener {
            aturMenuLayananAktif(binding.btnGridTutorial)
            startActivity(Intent(this, TutorialActivity::class.java))
        }

        // 4. Navigasi ke halaman Aktivitas & Peta TPS
        binding.navAktivitas.setOnClickListener {
            startActivity(Intent(this, activity_activity::class.java))
        }

        binding.tvViewAll.setOnClickListener {
            startActivity(Intent(this, activity_activity::class.java))
        }

        binding.navPeta.setOnClickListener {
            startActivity(Intent(this, petatps::class.java))
        }
        binding.btnScanBanner.setOnClickListener {
            startActivity(Intent(this, ScanQrActivity::class.java))
        }
        binding.fabMainScan.setOnClickListener {
            startActivity(Intent(this, ScanQrActivity::class.java))
        }

        // 5. Menu Dropdown Profil (Opsi Tutorial sudah dihapus sesuai permintaan)
        binding.cardAvatarContainer.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)

            popupMenu.menu.add(0, 1, 0, "Profil $username")
            popupMenu.menu.add(0, 2, 1, "Logout")

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    1 -> {
                        Toast.makeText(this, "Anda login sebagai $username", Toast.LENGTH_SHORT).show()
                        true
                    }
                    2 -> {
                        tampilkanDialogLogout()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    // Fungsi untuk mengubah tampilan tombol Layanan QRTrash menjadi aktif saat ditekan
    private fun aturMenuLayananAktif(tombolAktif: MaterialButton) {
        val daftarTombolLayanan = listOf(
            binding.btnGrid1,
            binding.btnGrid2,
            binding.btnGrid3,
            binding.btnGrid4,
            binding.btnGrid5,
            binding.btnGridTutorial
        )

        val bgAktif = ColorStateList.valueOf(Color.parseColor("#15803D"))
        val bgNonAktif = ColorStateList.valueOf(Color.parseColor("#DCFCE7"))
        val teksAktif = Color.WHITE
        val teksNonAktif = Color.parseColor("#15803D")
        val garisAktif = ColorStateList.valueOf(Color.parseColor("#4ADE80"))
        val ripplePutih = ColorStateList.valueOf(Color.parseColor("#33FFFFFF"))

        val ketebalanGarisPx = (2 * resources.displayMetrics.density).toInt()

        for (tombol in daftarTombolLayanan) {
            if (tombol == tombolAktif) {
                tombol.backgroundTintList = bgAktif
                tombol.setTextColor(teksAktif)
                tombol.strokeColor = garisAktif
                tombol.strokeWidth = ketebalanGarisPx
                tombol.rippleColor = ripplePutih
            } else {
                tombol.backgroundTintList = bgNonAktif
                tombol.setTextColor(teksNonAktif)
                tombol.strokeWidth = 0
            }
        }
    }

    private fun tampilkanDialogLogout() {
        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin keluar dari akun ini?")
            .setPositiveButton("Ya, Keluar") { dialog, _ ->
                val editor = sharedPref.edit()
                editor.clear()
                editor.apply()

                dialog.dismiss()

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}