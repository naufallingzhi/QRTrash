package com.example.qrtrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.qrtrash.databinding.FragmentJadwalHariBinding

// Data class untuk menyimpan informasi jadwal Pagi, Siang, dan Sore per hari
data class JadwalHarianModel(
    val namaHari: String,
    val rutePagi: String,
    val armadaPagi: String,
    val ruteSiang: String,
    val armadaSiang: String,
    val ruteSore: String,
    val armadaSore: String
)

class JadwalHariFragment(private val posisiHari: Int = 0) : Fragment() {

    private var _binding: FragmentJadwalHariBinding? = null
    private val binding get() = _binding!!

    // Daftar jadwal truk dari Senin (0) sampai Minggu (6)
    private val daftarJadwalMingguan = listOf(
        JadwalHarianModel(
            "Senin",
            "TPS Pasar Pagi Arengka & Jl. Soekarno Hatta",
            "Armada: Dump Truck DLHK-01 (BM 8011 TP)",
            "TPS Jl. HR. Soebrantas & Kawasan Panam",
            "Armada: Armroll Truck DLHK-04 (BM 8042 TP)",
            "TPS Jl. Garuda Sakti & Tuah Madani",
            "Armada: Compactor Truck DLHK-07 (BM 8075 TP)"
        ),
        JadwalHarianModel(
            "Selasa",
            "TPS Jl. Kaharuddin Nasution & Simpang Tiga",
            "Armada: Dump Truck DLHK-02 (BM 8015 TP)",
            "TPS Jl. Paus & Kawasan Marpoyan Damai",
            "Armada: Armroll Truck DLHK-05 (BM 8050 TP)",
            "TPS Jl. Arifin Ahmad & Sidomulyo Timur",
            "Armada: Compactor Truck DLHK-08 (BM 8081 TP)"
        ),
        JadwalHarianModel(
            "Rabu",
            "TPS Pasar Cik Puan & Jl. Tuanku Tambusai",
            "Armada: Dump Truck DLHK-03 (BM 8022 TP)",
            "TPS Jl. Riau & Kawasan Payung Sekaki",
            "Armada: Armroll Truck DLHK-06 (BM 8063 TP)",
            "TPS Jl. Durian & Kawasan Sukajadi",
            "Armada: Compactor Truck DLHK-09 (BM 8090 TP)"
        ),
        JadwalHarianModel(
            "Kamis",
            "TPS Pasar Rumbai & Jl. Yos Sudarso",
            "Armada: Dump Truck DLHK-01 (BM 8011 TP)",
            "TPS Sekitar Kampus PCR & Umban Sari",
            "Armada: Armroll Truck DLHK-04 (BM 8042 TP)",
            "TPS Jl. Paus Ujung & Limbungan Baru",
            "Armada: Compactor Truck DLHK-07 (BM 8075 TP)"
        ),
        JadwalHarianModel(
            "Jumat",
            "TPS Jl. Bukit Barisan & Tangkerang Timur",
            "Armada: Dump Truck DLHK-02 (BM 8015 TP)",
            "TPS Kawasan Kulim & Tenayan Raya",
            "Armada: Armroll Truck DLHK-05 (BM 8050 TP)",
            "TPS Jl. Hangtuah & Rejosari",
            "Armada: Compactor Truck DLHK-08 (BM 8081 TP)"
        ),
        JadwalHarianModel(
            "Sabtu",
            "TPS Jl. Jenderal Sudirman & Pusat Kota",
            "Armada: Dump Truck DLHK-03 (BM 8022 TP)",
            "TPS Jl. Diponegoro & Kawasan Sail",
            "Armada: Armroll Truck DLHK-06 (BM 8063 TP)",
            "TPS Jl. Harapan Raya & Bukit Raya",
            "Armada: Compactor Truck DLHK-09 (BM 8090 TP)"
        ),
        JadwalHarianModel(
            "Minggu",
            "TPS Area Car Free Day (CFD) Jl. Sudirman",
            "Armada: Compactor Khusus CFD (BM 8099 TP)",
            "TPS Taman Rekreasi Alam Mayang & Pusat Kuliner",
            "Armada: Armroll Truck DLHK-04 (BM 8042 TP)",
            "TPS Pasar Bawah & Kawasan Senapelan",
            "Armada: Dump Truck DLHK-01 (BM 8011 TP)"
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJadwalHariBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jadwal = daftarJadwalMingguan[posisiHari]

        with(binding) {
            tvHariHeader.text = "Jadwal Operasional Hari ${jadwal.namaHari}"

            // Isi Kartu Pagi
            tvRutePagi.text = jadwal.rutePagi
            tvArmadaPagi.text = jadwal.armadaPagi

            // Isi Kartu Siang
            tvRuteSiang.text = jadwal.ruteSiang
            tvArmadaSiang.text = jadwal.armadaSiang

            // Isi Kartu Sore
            tvRuteSore.text = jadwal.ruteSore
            tvArmadaSore.text = jadwal.armadaSore
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}