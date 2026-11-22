package com.example.qrtrash

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.qrtrash.data.AppDatabase
import com.example.qrtrash.data.entity.LaporanEntity
import com.example.qrtrash.databinding.ActivityTinjauLaporanBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TinjauLaporanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTinjauLaporanBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTinjauLaporanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        // 1. Ambil data otomatis dari halaman sebelumnya
        val idTps = intent.getStringExtra("EXTRA_ID_TPS") ?: "TPS-PKU-012"
        val lokasiTps = intent.getStringExtra("EXTRA_LOKASI_TPS") ?: "Jl. Jenderal Sudirman, Pekanbaru"
        val koordinat = intent.getStringExtra("EXTRA_KOORDINAT") ?: "0.5071° N, 101.4478° E"
        val kategori = intent.getStringExtra("EXTRA_KATEGORI") ?: "Sampah Penuh"

        // Format waktu pelaporan secara real-time
        val jamSekarang = SimpleDateFormat("HH.mm", Locale.getDefault()).format(Date())
        val waktuLaporan = "Hari ini, $jamSekarang WIB"

        // 2. Tampilkan ke layar Tinjau Laporan
        binding.tvDetailIdTps.text = idTps
        binding.tvDetailKategori.text = kategori
        binding.tvDetailKoordinat.text = koordinat
        binding.tvDetailLokasi.text = lokasiTps
        binding.tvDetailWaktu.text = waktuLaporan

        // 3. Tombol Kembali & Ambil Ulang
        binding.btnBackTinjau.setOnClickListener { finish() }
        binding.btnAmbilUlang.setOnClickListener { finish() }

        // 4. Tombol Kirim Laporan -> Simpan ke Room Database (Modul 12 Tahap 06)
        binding.btnKirimLaporan.setOnClickListener {
            val catatanInput = binding.etCatatan.text.toString().trim()
            val catatanFinal = if (catatanInput.isBlank()) {
                "Tidak ada catatan tambahan"
            } else {
                catatanInput
            }

            binding.btnKirimLaporan.isEnabled = false

            lifecycleScope.launch {
                try {
                    db.laporanDao().insert(
                        LaporanEntity(
                            idTps = idTps,
                            kategoriMasalah = kategori,
                            lokasiTps = lokasiTps,
                            koordinatGps = koordinat,
                            waktuLaporan = waktuLaporan,
                            catatan = catatanFinal,
                            status = "Pending"
                        )
                    )

                    Toast.makeText(
                        this@TinjauLaporanActivity,
                        "Laporan berhasil dikirim dan tersimpan!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Langsung arahkan ke Riwayat Laporan (Aktivitas) agar data baru langsung terlihat
                    val intent = Intent(this@TinjauLaporanActivity, activity_activity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                } catch (e: Exception) {
                    binding.btnKirimLaporan.isEnabled = true
                    Toast.makeText(
                        this@TinjauLaporanActivity,
                        "Gagal menyimpan laporan",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}