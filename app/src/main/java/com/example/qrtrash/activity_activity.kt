package com.example.qrtrash

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.qrtrash.data.AppDatabase
import com.example.qrtrash.data.entity.LaporanEntity
import com.example.qrtrash.databinding.ActivityActivityBinding
import kotlinx.coroutines.launch

class activity_activity : AppCompatActivity() {

    private lateinit var binding: ActivityActivityBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi Room Database & isi data dummy awal untuk tab Diterima dan Selesai
        db = AppDatabase.getInstance(this)
        isiDataDummyDiterimaDanSelesai()

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

        // 3. Listener BackStack: Sinkronkan tombol atas saat tombol Back HP ditekan
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

        binding.fabMainScan.setOnClickListener {
            startActivity(Intent(this, ScanQrActivity::class.java))
        }

        // 5. Navigasi Bottom Navigation: Beranda langsung kembali ke Home & bersihkan stack form
        binding.navBeranda.setOnClickListener {
            kembaliKeBeranda()
        }

        binding.navPeta.setOnClickListener {
            val intent = Intent(this, petatps::class.java)
            startActivity(intent)
            finish()
        }
    }

    // Mengisi data dummy ke Room Database hanya jika tab Diterima / Selesai masih kosong
    private fun isiDataDummyDiterimaDanSelesai() {
        lifecycleScope.launch {
            val sekarang = System.currentTimeMillis()

            // A. Cek & isi data dummy untuk tab "Diterima" (6 item agar bisa di-scroll)
            if (db.laporanDao().getByStatus("Diterima").isEmpty()) {
                val dummyDiterima = listOf(
                    LaporanEntity(
                        idTps = "TPS-PKU-004",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Jl. HR. Soebrantas, Panam",
                        koordinatGps = "0.4633° N, 101.3849° E",
                        waktuLaporan = "Hari ini, 09.15 WIB",
                        catatan = "Truk petugas sedang menuju lokasi",
                        status = "Diterima",
                        imageUrl = "https://picsum.photos/seed/pku_diterima_1/400/300",
                        createdAt = sekarang - 1 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-008",
                        kategoriMasalah = "Tempat Sampah Rusak",
                        lokasiTps = "Jl. Paus, Marpoyan Damai",
                        koordinatGps = "0.4935° N, 101.4395° E",
                        waktuLaporan = "Hari ini, 08.40 WIB",
                        catatan = "Engsel penutup patah, menunggu perbaikan",
                        status = "Diterima",
                        imageUrl = "https://picsum.photos/seed/pku_diterima_2/400/300",
                        createdAt = sekarang - 2 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-015",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Pasar Pagi Arengka, Jl. Soekarno Hatta",
                        koordinatGps = "0.4682° N, 101.4184° E",
                        waktuLaporan = "Hari ini, 08.10 WIB",
                        catatan = "Sampah organik mulai meluber ke bahu jalan",
                        status = "Diterima",
                        imageUrl = "https://picsum.photos/seed/pku_diterima_3/400/300",
                        createdAt = sekarang - 3 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-021",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Jl. Umban Sari, Rumbai",
                        koordinatGps = "0.5703° N, 101.4255° E",
                        waktuLaporan = "Hari ini, 07.50 WIB",
                        catatan = "Sudah dijadwalkan pengangkutan shift pagi",
                        status = "Diterima",
                        imageUrl = "https://picsum.photos/seed/pku_diterima_4/400/300",
                        createdAt = sekarang - 4 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-027",
                        kategoriMasalah = "Tempat Sampah Rusak",
                        lokasiTps = "Jl. Tuanku Tambusai, Sukajadi",
                        koordinatGps = "0.5177° N, 101.4337° E",
                        waktuLaporan = "Kemarin, 17.20 WIB",
                        catatan = "Bagian bawah wadah berlubang",
                        status = "Diterima",
                        imageUrl = "https://picsum.photos/seed/pku_diterima_5/400/300",
                        createdAt = sekarang - 5 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-033",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Jl. Kaharuddin Nasution, Simpang Tiga",
                        koordinatGps = "0.4748° N, 101.4562° E",
                        waktuLaporan = "Kemarin, 16.45 WIB",
                        catatan = "Laporan diverifikasi admin DLHK",
                        status = "Diterima",
                        imageUrl = "https://picsum.photos/seed/pku_diterima_6/400/300",
                        createdAt = sekarang - 6 * 600_000L
                    )
                )

                for (item in dummyDiterima) {
                    db.laporanDao().insert(item)
                }
            }

            // B. Cek & isi data dummy untuk tab "Selesai" (6 item agar bisa di-scroll)
            if (db.laporanDao().getByStatus("Selesai").isEmpty()) {
                val dummySelesai = listOf(
                    LaporanEntity(
                        idTps = "TPS-PKU-002",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Jl. Jenderal Sudirman, Pekanbaru",
                        koordinatGps = "0.5071° N, 101.4478° E",
                        waktuLaporan = "Kemarin, 14.30 WIB",
                        catatan = "Sudah diangkut bersih oleh petugas",
                        status = "Selesai",
                        imageUrl = "https://picsum.photos/seed/pku_selesai_1/400/300",
                        createdAt = sekarang - 1 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-009",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Jl. Bukit Barisan, Tenayan Raya",
                        koordinatGps = "0.5067° N, 101.5150° E",
                        waktuLaporan = "Kemarin, 11.15 WIB",
                        catatan = "Pengangkutan selesai dan area disemprot",
                        status = "Selesai",
                        imageUrl = "https://picsum.photos/seed/pku_selesai_2/400/300",
                        createdAt = sekarang - 2 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-014",
                        kategoriMasalah = "Tempat Sampah Rusak",
                        lokasiTps = "Taman Rekreasi Alam Mayang",
                        koordinatGps = "0.4956° N, 101.5030° E",
                        waktuLaporan = "2 hari lalu, 16.05 WIB",
                        catatan = "Unit tempat sampah telah diganti baru",
                        status = "Selesai",
                        imageUrl = "https://picsum.photos/seed/pku_selesai_3/400/300",
                        createdAt = sekarang - 3 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-019",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Pasar Cik Puan, Sukajadi",
                        koordinatGps = "0.5177° N, 101.4337° E",
                        waktuLaporan = "2 hari lalu, 10.20 WIB",
                        catatan = "Truk angkutan sudah mengosongkan bak",
                        status = "Selesai",
                        imageUrl = "https://picsum.photos/seed/pku_selesai_4/400/300",
                        createdAt = sekarang - 4 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-024",
                        kategoriMasalah = "Sampah Penuh",
                        lokasiTps = "Jl. Arifin Ahmad, Marpoyan Damai",
                        koordinatGps = "0.4840° N, 101.4450° E",
                        waktuLaporan = "3 hari lalu, 09.00 WIB",
                        catatan = "Sudah ditangani tim kebersihan shift pagi",
                        status = "Selesai",
                        imageUrl = "https://picsum.photos/seed/pku_selesai_5/400/300",
                        createdAt = sekarang - 5 * 600_000L
                    ),
                    LaporanEntity(
                        idTps = "TPS-PKU-030",
                        kategoriMasalah = "Tempat Sampah Rusak",
                        lokasiTps = "Jl. Riau, Senapelan",
                        koordinatGps = "0.5262° N, 101.4305° E",
                        waktuLaporan = "4 hari lalu, 15.40 WIB",
                        catatan = "Penutup dan stiker QR sudah diperbarui",
                        status = "Selesai",
                        imageUrl = "https://picsum.photos/seed/pku_selesai_6/400/300",
                        createdAt = sekarang - 6 * 600_000L
                    )
                )

                for (item in dummySelesai) {
                    db.laporanDao().insert(item)
                }
            }
        }
    }

    // Fungsi kembali ke Beranda (Home) sekaligus membersihkan sisa halaman form di back stack
    private fun kembaliKeBeranda() {
        val intent = Intent(this, activity_main::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
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

    // Tombol Back di Toolbar atas langsung kembali ke Beranda (Home)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                kembaliKeBeranda()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}