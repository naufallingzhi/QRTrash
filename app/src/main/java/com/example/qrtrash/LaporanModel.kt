package com.example.qrtrash

data class LaporanModel(
    val lokasiTps: String,
    val kendala: String,
    val waktu: String,
    val status: String,
    val imageUrl: String
) {
    companion object {
        // Sumber Data Terpusat (Modul 10 Tahap 4.6)
        val semuaLaporan = listOf(
            // --- DATA STATUS: PENDING ---
            LaporanModel(
                "TPS Pasar Pagi Arengka",
                "Kendala: Kontainer Sampah Penuh Meluber",
                "Hari ini, 08:30 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_arengka/400/300"
            ),
            LaporanModel(
                "TPS Jl. HR. Soebrantas (Panam)",
                "Kendala: Tumpukan Sampah Liar di Pinggir Jalan",
                "Hari ini, 09:15 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_panam/400/300"
            ),
            LaporanModel(
                "TPS Jl. Kaharuddin Nasution",
                "Kendala: Bak Sampah Besi Rusak & Miring",
                "Kemarin, 17:40 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_marpoyan/400/300"
            ),
            LaporanModel(
                "TPS Taman Rekreasi Alam Mayang",
                "Kendala: Tong Sampah Organik Penuh",
                "Kemarin, 16:10 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_mayang/400/300"
            ),
            LaporanModel(
                "TPS Jl. HR. Soebrantas (Panam)",
                "Kendala: Tumpukan Sampah Liar di Pinggir Jalan",
                "Hari ini, 09:15 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_panam/400/300"
            ),
            LaporanModel(
                "TPS Jl. Kaharuddin Nasution",
                "Kendala: Bak Sampah Besi Rusak & Miring",
                "Kemarin, 17:40 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_marpoyan/400/300"
            ),
            LaporanModel(
                "TPS Taman Rekreasi Alam Mayang",
                "Kendala: Tong Sampah Organik Penuh",
                "Kemarin, 16:10 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_mayang/400/300"
            ),
            LaporanModel(
                "TPS Jl. HR. Soebrantas (Panam)",
                "Kendala: Tumpukan Sampah Liar di Pinggir Jalan",
                "Hari ini, 09:15 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_panam/400/300"
            ),
            LaporanModel(
                "TPS Jl. Kaharuddin Nasution",
                "Kendala: Bak Sampah Besi Rusak & Miring",
                "Kemarin, 17:40 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_marpoyan/400/300"
            ),
            LaporanModel(
                "TPS Taman Rekreasi Alam Mayang",
                "Kendala: Tong Sampah Organik Penuh",
                "Kemarin, 16:10 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_mayang/400/300"
            ),
            LaporanModel(
                "TPS Jl. HR. Soebrantas (Panam)",
                "Kendala: Tumpukan Sampah Liar di Pinggir Jalan",
                "Hari ini, 09:15 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_panam/400/300"
            ),
            LaporanModel(
                "TPS Jl. Kaharuddin Nasution",
                "Kendala: Bak Sampah Besi Rusak & Miring",
                "Kemarin, 17:40 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_marpoyan/400/300"
            ),
            LaporanModel(
                "TPS Taman Rekreasi Alam Mayang",
                "Kendala: Tong Sampah Organik Penuh",
                "Kemarin, 16:10 WIB",
                "Pending",
                "https://picsum.photos/seed/tps_mayang/400/300"
            ),

            // --- DATA STATUS: DITERIMA ---
            LaporanModel(
                "TPS Jl. Paus (Marpoyan Damai)",
                "Kendala: Sampah Pasar Belum Diangkut",
                "Hari ini, 07:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_paus/400/300"
            ),
            LaporanModel(
                "TPS Sekitar Kampus PCR Rumbai",
                "Kendala: Tempat Sampah Plastik Penuh",
                "Kemarin, 14:20 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_pcr/400/300"
            ),
            LaporanModel(
                "TPS Jl. Bukit Barisan (Tenayan Raya)",
                "Kendala: Bau Menyengat & Sampah Menumpuk",
                "2 hari lalu, 11:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_tenayan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Paus (Marpoyan Damai)",
                "Kendala: Sampah Pasar Belum Diangkut",
                "Hari ini, 07:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_paus/400/300"
            ),
            LaporanModel(
                "TPS Sekitar Kampus PCR Rumbai",
                "Kendala: Tempat Sampah Plastik Penuh",
                "Kemarin, 14:20 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_pcr/400/300"
            ),
            LaporanModel(
                "TPS Jl. Bukit Barisan (Tenayan Raya)",
                "Kendala: Bau Menyengat & Sampah Menumpuk",
                "2 hari lalu, 11:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_tenayan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Paus (Marpoyan Damai)",
                "Kendala: Sampah Pasar Belum Diangkut",
                "Hari ini, 07:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_paus/400/300"
            ),
            LaporanModel(
                "TPS Sekitar Kampus PCR Rumbai",
                "Kendala: Tempat Sampah Plastik Penuh",
                "Kemarin, 14:20 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_pcr/400/300"
            ),
            LaporanModel(
                "TPS Jl. Bukit Barisan (Tenayan Raya)",
                "Kendala: Bau Menyengat & Sampah Menumpuk",
                "2 hari lalu, 11:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_tenayan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Paus (Marpoyan Damai)",
                "Kendala: Sampah Pasar Belum Diangkut",
                "Hari ini, 07:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_paus/400/300"
            ),
            LaporanModel(
                "TPS Sekitar Kampus PCR Rumbai",
                "Kendala: Tempat Sampah Plastik Penuh",
                "Kemarin, 14:20 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_pcr/400/300"
            ),
            LaporanModel(
                "TPS Jl. Bukit Barisan (Tenayan Raya)",
                "Kendala: Bau Menyengat & Sampah Menumpuk",
                "2 hari lalu, 11:00 WIB",
                "Diterima",
                "https://picsum.photos/seed/tps_tenayan/400/300"
            ),

            // --- DATA STATUS: SELESAI ---
            LaporanModel(
                "TPS Jl. Jenderal Sudirman",
                "Kendala: Kontainer Penuh (Sudah Diangkut)",
                "3 hari lalu, 06:45 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_sudirman/400/300"
            ),
            LaporanModel(
                "TPS Pasar Cik Puan",
                "Kendala: Tumpukan Sampah Sayur (Sudah Bersih)",
                "4 hari lalu, 16:30 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_cikpuan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Riau Ujung",
                "Kendala: Penggantian Stiker QR Code Baru",
                "5 hari lalu, 10:00 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_riau/400/300"
            ),
            LaporanModel(
                "TPS Jl. Tuanku Tambusai (Nangka)",
                "Kendala: Pembersihan Bak Sampah Umum",
                "1 minggu lalu, 08:00 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_nangka/400/300"
            ),
            LaporanModel(
                "TPS Jl. Jenderal Sudirman",
                "Kendala: Kontainer Penuh (Sudah Diangkut)",
                "3 hari lalu, 06:45 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_sudirman/400/300"
            ),
            LaporanModel(
                "TPS Pasar Cik Puan",
                "Kendala: Tumpukan Sampah Sayur (Sudah Bersih)",
                "4 hari lalu, 16:30 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_cikpuan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Riau Ujung",
                "Kendala: Penggantian Stiker QR Code Baru",
                "5 hari lalu, 10:00 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_riau/400/300"
            ),
            LaporanModel(
                "TPS Jl. Jenderal Sudirman",
                "Kendala: Kontainer Penuh (Sudah Diangkut)",
                "3 hari lalu, 06:45 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_sudirman/400/300"
            ),
            LaporanModel(
                "TPS Pasar Cik Puan",
                "Kendala: Tumpukan Sampah Sayur (Sudah Bersih)",
                "4 hari lalu, 16:30 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_cikpuan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Riau Ujung",
                "Kendala: Penggantian Stiker QR Code Baru",
                "5 hari lalu, 10:00 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_riau/400/300"
            ),
            LaporanModel(
                "TPS Jl. Jenderal Sudirman",
                "Kendala: Kontainer Penuh (Sudah Diangkut)",
                "3 hari lalu, 06:45 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_sudirman/400/300"
            ),
            LaporanModel(
                "TPS Pasar Cik Puan",
                "Kendala: Tumpukan Sampah Sayur (Sudah Bersih)",
                "4 hari lalu, 16:30 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_cikpuan/400/300"
            ),
            LaporanModel(
                "TPS Jl. Riau Ujung",
                "Kendala: Penggantian Stiker QR Code Baru",
                "5 hari lalu, 10:00 WIB",
                "Selesai",
                "https://picsum.photos/seed/tps_riau/400/300"
            )
        )
    }
}