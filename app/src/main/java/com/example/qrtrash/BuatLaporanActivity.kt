package com.example.qrtrash

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityBuatLaporanBinding

class BuatLaporanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuatLaporanBinding
    private var kategoriTerpilih: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuatLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Ambil data fasilitas hasil scan QR dari Intent
        val idTps = intent.getStringExtra("EXTRA_ID_TPS") ?: "TPS-PKU-012"
        val lokasiTps = intent.getStringExtra("EXTRA_LOKASI_TPS") ?: "Jl. Jenderal Sudirman, Pekanbaru"
        val koordinat = intent.getStringExtra("EXTRA_KOORDINAT") ?: "0.5071° N, 101.4478° E"

        binding.tvKodeTps.text = "Tempat Sampah: $idTps"
        binding.tvAlamatTps.text = lokasiTps

        // 2. Tombol Kembali & Tombol Bantuan
        binding.btnBackBuat.setOnClickListener {
            finish()
        }

        binding.btnBantuan.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Panduan Buat Laporan")
                .setMessage("1. Pilih salah satu jenis kendala yang terjadi pada tempat sampah.\n2. Arahkan kamera ke kondisi tempat sampah, lalu tekan tombol kamera hijau di bawah untuk melanjutkan.")
                .setPositiveButton("Mengerti") { dialog, _ -> dialog.dismiss() }
                .show()
        }

        // 3. Aksi Pilih Kendala (Radio Card Interaktif)
        binding.cardOpsiPenuh.setOnClickListener {
            pilihKendala("Sampah Penuh")
        }

        binding.cardOpsiRusak.setOnClickListener {
            pilihKendala("Tempat Sampah Rusak")
        }

        // 4. Tombol Kamera -> Validasi lalu pindah ke halaman Tinjau Laporan
        binding.btnAmbilFotoOuter.setOnClickListener {
            if (kategoriTerpilih.isBlank()) {
                Toast.makeText(this, "Pilih satu kendala terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Foto kondisi berhasil diambil", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, TinjauLaporanActivity::class.java)
            intent.putExtra("EXTRA_ID_TPS", idTps)
            intent.putExtra("EXTRA_LOKASI_TPS", lokasiTps)
            intent.putExtra("EXTRA_KOORDINAT", koordinat)
            intent.putExtra("EXTRA_KATEGORI", kategoriTerpilih)
            startActivity(intent)
        }
    }

    private fun pilihKendala(kategori: String) {
        kategoriTerpilih = kategori

        val hijauTua = Color.parseColor("#15803D")
        val hijauMuda = Color.parseColor("#86EFAC")
        val hijauAktif = Color.parseColor("#16A34A")
        val hijauBg = Color.parseColor("#ECFDF5")

        if (kategori == "Sampah Penuh") {
            binding.cardOpsiPenuh.strokeColor = hijauTua
            binding.cardOpsiPenuh.setCardBackgroundColor(hijauBg)
            binding.radioPenuh.setCardBackgroundColor(hijauAktif)
            binding.ivCheckPenuh.visibility = View.VISIBLE

            binding.cardOpsiRusak.strokeColor = hijauMuda
            binding.cardOpsiRusak.setCardBackgroundColor(Color.WHITE)
            binding.radioRusak.setCardBackgroundColor(Color.WHITE)
            binding.ivCheckRusak.visibility = View.GONE
        } else {
            binding.cardOpsiRusak.strokeColor = hijauTua
            binding.cardOpsiRusak.setCardBackgroundColor(hijauBg)
            binding.radioRusak.setCardBackgroundColor(hijauAktif)
            binding.ivCheckRusak.visibility = View.VISIBLE

            binding.cardOpsiPenuh.strokeColor = hijauMuda
            binding.cardOpsiPenuh.setCardBackgroundColor(Color.WHITE)
            binding.radioPenuh.setCardBackgroundColor(Color.WHITE)
            binding.ivCheckPenuh.visibility = View.GONE
        }
    }
}