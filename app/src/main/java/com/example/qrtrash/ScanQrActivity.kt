package com.example.qrtrash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityScanQrBinding

class ScanQrActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScanQrBinding
    private var isFlashOn = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScanQrBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Tombol Kembali
        binding.btnBackScan.setOnClickListener {
            finish()
        }

        // Simulasi Scan QR Code: saat bingkai diketuk, fasilitas terdeteksi otomatis
        binding.areaScanQr.setOnClickListener {
            Toast.makeText(this, "QR Code Terdeteksi: TPS-PKU-012", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, BuatLaporanActivity::class.java)
            intent.putExtra("EXTRA_ID_TPS", "TPS-PKU-012")
            intent.putExtra("EXTRA_LOKASI_TPS", "Jl. Jenderal Sudirman, Pekanbaru")
            intent.putExtra("EXTRA_KOORDINAT", "0.5071° N, 101.4478° E")
            startActivity(intent)
        }

        // Simulasi Toggle Lampu Flash
        binding.btnFlash.setOnClickListener {
            isFlashOn = !isFlashOn
            if (isFlashOn) {
                binding.btnFlash.setCardBackgroundColor(Color.parseColor("#FEF3C7"))
                binding.ivFlashIcon.setColorFilter(Color.parseColor("#D97706"))
                binding.tvLabelFlash.text = "Matikan Lampu Flash"
                Toast.makeText(this, "Lampu Flash Dinyalakan", Toast.LENGTH_SHORT).show()
            } else {
                binding.btnFlash.setCardBackgroundColor(Color.parseColor("#F0FDF4"))
                binding.ivFlashIcon.setColorFilter(Color.parseColor("#334155"))
                binding.tvLabelFlash.text = "Nyalakan Lampu Flash"
                Toast.makeText(this, "Lampu Flash Dimatikan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}