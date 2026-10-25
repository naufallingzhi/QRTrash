package com.example.qrtrash

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityMainBinding

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
            // Hapus tanda agar tidak muncul berulang saat rotasi layar
            intent.removeExtra("SHOW_WELCOME_POPUP")
        }

        // 3. Navigasi ke halaman Aktivitas
        binding.navAktivitas.setOnClickListener {
            val intent = Intent(this, activity_activity::class.java)
            startActivity(intent)
        }

        // 4. Menu Dropdown (PopupMenu) saat menekan Foto Profil di pojok kiri atas
        binding.cardAvatarContainer.setOnClickListener { view ->
            val popupMenu = PopupMenu(this, view)

            // Menambahkan item ke dalam menu dropdown
            popupMenu.menu.add(0, 1, 0, "Profil $username")
            popupMenu.menu.add(0, 2, 1, "Logout")

            popupMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    1 -> {
                        Toast.makeText(this, "Anda login sebagai $username", Toast.LENGTH_SHORT).show()
                        true
                    }
                    2 -> {
                        // Munculkan dialog konfirmasi Logout saat opsi Logout di dropdown dipilih
                        tampilkanDialogLogout()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
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