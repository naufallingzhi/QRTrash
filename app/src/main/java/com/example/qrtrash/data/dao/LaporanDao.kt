package com.example.qrtrash.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.qrtrash.data.entity.LaporanEntity

@Dao
interface LaporanDao {

    @Query("SELECT * FROM laporan_tps ORDER BY createdAt DESC")
    suspend fun getAll(): List<LaporanEntity>

    @Query("SELECT * FROM laporan_tps WHERE status = :status ORDER BY createdAt DESC")
    suspend fun getByStatus(status: String): List<LaporanEntity>

    @Insert
    suspend fun insert(laporan: LaporanEntity)

    @Delete
    suspend fun delete(laporan: LaporanEntity)
}