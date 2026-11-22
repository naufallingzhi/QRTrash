package com.example.qrtrash

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.qrtrash.databinding.ActivityPetatpsBinding
import com.google.android.material.chip.Chip
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.XYTileSource
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.Marker
import java.io.File

class petatps : AppCompatActivity() {

    private lateinit var binding: ActivityPetatpsBinding

    // Model sederhana untuk daftar titik marker TPS di Pekanbaru
    private data class TitikTps(
        val nama: String,
        val keterangan: String,
        val wilayah: String,
        val lat: Double,
        val lon: Double
    )

    // Daftar titik TPS di berbagai wilayah Pekanbaru
    private val daftarTpsPekanbaru = listOf(
        TitikTps(
            "TPS-PKU-012 • Jl. Jenderal Sudirman",
            "Status: Aktif • Pusat Kota Pekanbaru",
            "Sudirman",
            0.5071, 101.4478
        ),
        TitikTps(
            "TPS Pasar Pagi Arengka",
            "Status: Padat • Jl. Soekarno Hatta",
            "Panam",
            0.4682, 101.4184
        ),
        TitikTps(
            "TPS Jl. HR. Soebrantas (Panam)",
            "Status: Aktif • Kawasan Panam / Tuah Madani",
            "Panam",
            0.4633, 101.3849
        ),
        TitikTps(
            "TPS Jl. Paus (Marpoyan Damai)",
            "Status: Aktif • Kecamatan Marpoyan Damai",
            "Marpoyan",
            0.4935, 101.4395
        ),
        TitikTps(
            "TPS Jl. Kaharuddin Nasution",
            "Status: Aktif • Simpang Tiga / Marpoyan",
            "Marpoyan",
            0.4748, 101.4562
        ),
        TitikTps(
            "TPS Sekitar Kampus PCR Rumbai",
            "Status: Aktif • Umban Sari, Rumbai",
            "Rumbai",
            0.5703, 101.4255
        ),
        TitikTps(
            "TPS Jl. Bukit Barisan & Kulim",
            "Status: Aktif • Tenayan Raya",
            "Tenayan",
            0.5067, 101.5150
        ),
        TitikTps(
            "TPS Taman Rekreasi Alam Mayang",
            "Status: Aktif • Tangkerang Timur",
            "Tenayan",
            0.4956, 101.5030
        ),
        TitikTps(
            "TPS Pasar Cik Puan",
            "Status: Aktif • Jl. Tuanku Tambusai, Sukajadi",
            "Sukajadi",
            0.5177, 101.4337
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // WAJIB dijalankan sebelum inflate layout MapView:
        // Mengatur User-Agent & folder cache internal agar tile peta tampil normal di Android modern
        val osmConfig = Configuration.getInstance()
        osmConfig.userAgentValue = "QRTrashPekanbaru/1.0"
        osmConfig.osmdroidBasePath = File(cacheDir, "osmdroid")
        osmConfig.osmdroidTileCache = File(osmConfig.osmdroidBasePath, "tiles")

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

        // 2. Konfigurasi Awal Peta OpenStreetMap (Pusat Kota Pekanbaru)
        setupPetaPekanbaru()

        // 3. Deteksi Chip Wilayah yang dipilih -> Geser & Zoom peta otomatis
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                val namaWilayah = chip.text.toString()

                binding.tvMapBadge.text = "📍 Peta Sebaran TPS: $namaWilayah"
                Toast.makeText(this, "Filter Wilayah: $namaWilayah", Toast.LENGTH_SHORT).show()

                arahkanPetaKeWilayah(namaWilayah)
            }
        }

        // 4. Navigasi Menu Bawah: Klik Beranda -> kembali ke Dashboard
        binding.navBeranda.setOnClickListener {
            val intent = Intent(this, activity_main::class.java)
            startActivity(intent)
            finish()
        }

        binding.fabMainScan.setOnClickListener {
            startActivity(Intent(this, ScanQrActivity::class.java))
        }

        // 5. Navigasi Menu Bawah: Klik Aktivitas -> pindah ke Halaman Aktivitas
        binding.navAktivitas.setOnClickListener {
            val intent = Intent(this, activity_activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupPetaPekanbaru() {
        val mapView = binding.mapViewPekanbaru

        // Kembali ke OpenStreetMap dengan nama source baru agar tidak memuat cache 403 lama
        val osmPekanbaru = XYTileSource(
            "OSM_QRTrash",
            0,
            19,
            256,
            ".png",
            arrayOf("https://tile.openstreetmap.org/")
        )

        mapView.setTileSource(osmPekanbaru)
        mapView.setMultiTouchControls(true) // Aktifkan pinch-to-zoom dengan dua jari
        mapView.isTilesScaledToDpi = true
        mapView.minZoomLevel = 10.0
        mapView.maxZoomLevel = 19.0

        // Titik awal: Pusat Kota Pekanbaru (Jl. Jenderal Sudirman)
        val pusatPekanbaru = GeoPoint(0.5071, 101.4478)
        mapView.controller.setZoom(13.0)
        mapView.controller.setCenter(pusatPekanbaru)

        // Pasang semua pin (marker) TPS Pekanbaru ke peta
        tampilkanMarkerTps(daftarTpsPekanbaru)
    }

    private fun tampilkanMarkerTps(listTps: List<TitikTps>) {
        val mapView = binding.mapViewPekanbaru
        mapView.overlays.clear()

        for (tps in listTps) {
            val marker = Marker(mapView)
            marker.position = GeoPoint(tps.lat, tps.lon)
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = tps.nama
            marker.snippet = tps.keterangan
            mapView.overlays.add(marker)
        }

        mapView.invalidate()
    }

    private fun arahkanPetaKeWilayah(namaWilayah: String) {
        val mapController = binding.mapViewPekanbaru.controller

        when {
            namaWilayah.contains("Panam", ignoreCase = true) ||
                    namaWilayah.contains("Tampan", ignoreCase = true) ||
                    namaWilayah.contains("Tuah", ignoreCase = true) -> {
                mapController.animateTo(GeoPoint(0.4658, 101.4016), 14.0, 800L)
            }
            namaWilayah.contains("Marpoyan", ignoreCase = true) -> {
                mapController.animateTo(GeoPoint(0.4840, 101.4450), 14.0, 800L)
            }
            namaWilayah.contains("Rumbai", ignoreCase = true) -> {
                mapController.animateTo(GeoPoint(0.5703, 101.4255), 14.5, 800L)
            }
            namaWilayah.contains("Tenayan", ignoreCase = true) ||
                    namaWilayah.contains("Kulim", ignoreCase = true) -> {
                mapController.animateTo(GeoPoint(0.5011, 101.5090), 14.0, 800L)
            }
            namaWilayah.contains("Sukajadi", ignoreCase = true) -> {
                mapController.animateTo(GeoPoint(0.5177, 101.4337), 14.5, 800L)
            }
            namaWilayah.contains("Sudirman", ignoreCase = true) ||
                    namaWilayah.contains("Pusat", ignoreCase = true) -> {
                mapController.animateTo(GeoPoint(0.5071, 101.4478), 14.5, 800L)
            }
            else -> {
                // Default ("Semua" atau wilayah lainnya): kembali ke tampilan luas Kota Pekanbaru
                mapController.animateTo(GeoPoint(0.5071, 101.4478), 12.5, 800L)
            }
        }
    }

    // Wajib untuk siklus hidup MapView osmdroid agar peta tidak hang saat pindah halaman
    override fun onResume() {
        super.onResume()
        binding.mapViewPekanbaru.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapViewPekanbaru.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapViewPekanbaru.onDetach()
    }

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