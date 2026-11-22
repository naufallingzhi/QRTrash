package com.example.qrtrash

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qrtrash.data.entity.LaporanEntity
import com.example.qrtrash.databinding.ItemLaporanBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LaporanAdapter(
    private val laporanList: List<LaporanEntity>,
    private val onItemClick: (LaporanEntity) -> Unit,
    private val onDelete: (LaporanEntity) -> Unit
) : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {

    inner class LaporanViewHolder(val binding: ItemLaporanBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaporanViewHolder {
        val binding = ItemLaporanBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LaporanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LaporanViewHolder, position: Int) {
        val item = laporanList[position]

        with(holder.binding) {
            tvLokasiTps.text = "${item.idTps} • ${item.lokasiTps}"
            tvKendala.text = "${item.kategoriMasalah} (${item.catatan})"
            tvWaktu.text = item.waktuLaporan
            tvStatusBadge.text = item.status

            when (item.status) {
                "Pending" -> {
                    cardBadgeStatus.setCardBackgroundColor(Color.parseColor("#FEF3C7"))
                    tvStatusBadge.setTextColor(Color.parseColor("#D97706"))
                }
                "Diterima" -> {
                    cardBadgeStatus.setCardBackgroundColor(Color.parseColor("#E0F2FE"))
                    tvStatusBadge.setTextColor(Color.parseColor("#0284C7"))
                }
                else -> {
                    cardBadgeStatus.setCardBackgroundColor(Color.parseColor("#DCFCE7"))
                    tvStatusBadge.setTextColor(Color.parseColor("#15803D"))
                }
            }

            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.logoaplikasi)
                .into(ivFotoLaporan)

            root.setOnClickListener {
                onItemClick(item)
            }

            // Dialog Konfirmasi Hapus (Modul 12 Tahap 07)
            btnDelete.setOnClickListener {
                MaterialAlertDialogBuilder(holder.itemView.context)
                    .setTitle("Hapus Laporan")
                    .setMessage("Yakin ingin menghapus laporan ${item.idTps} ini?")
                    .setPositiveButton("Ya") { _, _ -> onDelete(item) }
                    .setNegativeButton("Batal", null)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int = laporanList.size
}