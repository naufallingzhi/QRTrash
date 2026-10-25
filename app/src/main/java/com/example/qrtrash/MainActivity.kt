package com.example.qrtrash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityLoginBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

        binding.button.setOnClickListener {
            val username = binding.editTextText.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString()

            // Validasi: Username tidak kosong dan sama persis dengan password
            if (username.isNotEmpty() && username == password) {
                val editor = sharedPref.edit()
                editor.putBoolean("isLogin", true)
                editor.putString("username", username)
                editor.apply()

                // Pindah ke Dashboard sambil mengirim sinyal untuk memunculkan Pop-up Selamat Datang
                val intent = Intent(this, activity_main::class.java)
                intent.putExtra("SHOW_WELCOME_POPUP", true)
                startActivity(intent)
                finish()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Login Gagal")
                    .setMessage("Silahkan coba lagi. Pastikan username dan password benar!.")
                    .setPositiveButton("OK") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}