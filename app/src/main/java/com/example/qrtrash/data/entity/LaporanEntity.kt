package com.example.qrtrash.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "laporan_tps")
data class LaporanEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idTps: String,
    val kategoriMasalah: String,
    val lokasiTps: String,
    val koordinatGps: String,
    val waktuLaporan: String,
    val catatan: String,
    val status: String = "Pending",
    val imageUrl: String = "https://picsum.photos/seed/tps_pku/400/300",
    val createdAt: Long = System.currentTimeMillis()
)