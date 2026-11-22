package com.example.qrtrash.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.qrtrash.data.dao.LaporanDao
import com.example.qrtrash.data.entity.LaporanEntity

@Database(
    entities = [LaporanEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun laporanDao(): LaporanDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "qrtrash_database"
                ).build().also { INSTANCE = it }
            }
    }
}