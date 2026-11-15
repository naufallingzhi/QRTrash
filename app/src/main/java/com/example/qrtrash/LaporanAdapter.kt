package com.example.qrtrash

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qrtrash.databinding.ItemLaporanBinding

// ================= KODE BARU MODUL 10: RECYCLERVIEW ADAPTER =================
class LaporanAdapter(
    private val laporanList: List<LaporanModel>,
    private val onItemClick: (LaporanModel) -> Unit
) : RecyclerView.Adapter<LaporanAdapter.LaporanViewHolder>() {

    // ViewHolder menyimpan referensi komponen item_laporan.xml via ViewBinding
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
            tvLokasiTps.text = item.lokasiTps
            tvKendala.text = item.kendala
            tvWaktu.text = item.waktu
            tvStatusBadge.text = item.status

            // Atur warna badge untuk setiap kondisi agar tidak terbawa saat baris di-recycle (Modul 10 Tahap 3.4)
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

            // Memuat gambar dari URL menggunakan Glide
            Glide.with(holder.itemView.context)
                .load(item.imageUrl)
                .placeholder(R.drawable.logoaplikasi)
                .into(ivFotoLaporan)

            // Aksi klik item diteruskan ke Fragment lewat lambda onItemClick
            root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun getItemCount(): Int = laporanList.size
}

/*
// ================= KODE LAMA MODUL 7: LISTVIEW ARRAYADAPTER (DIKOMENTARI UNTUK PERBANDINGAN) =================
import android.content.Context
import android.view.View
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar

class LaporanAdapter(
    context: Context,
    private val listLaporan: List<LaporanModel>
) : ArrayAdapter<LaporanModel>(context, 0, listLaporan) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemLaporanBinding
        val view: View

        if (convertView == null) {
            binding = ItemLaporanBinding.inflate(LayoutInflater.from(context), parent, false)
            view = binding.root
            view.tag = binding
        } else {
            view = convertView
            binding = view.tag as ItemLaporanBinding
        }

        val data = listLaporan[position]
        binding.tvLokasiTps.text = data.lokasiTps
        binding.tvKendala.text = data.kendala
        binding.tvWaktu.text = data.waktu
        binding.tvStatusBadge.text = data.status

        when (data.status) {
            "Pending" -> {
                binding.cardBadgeStatus.setCardBackgroundColor(Color.parseColor("#FEF3C7"))
                binding.tvStatusBadge.setTextColor(Color.parseColor("#D97706"))
            }
            "Diterima" -> {
                binding.cardBadgeStatus.setCardBackgroundColor(Color.parseColor("#E0F2FE"))
                binding.tvStatusBadge.setTextColor(Color.parseColor("#0284C7"))
            }
            "Selesai" -> {
                binding.cardBadgeStatus.setCardBackgroundColor(Color.parseColor("#DCFCE7"))
                binding.tvStatusBadge.setTextColor(Color.parseColor("#15803D"))
            }
        }

        Glide.with(context)
            .load(data.imageUrl)
            .placeholder(R.drawable.logoaplikasi)
            .into(binding.ivFotoLaporan)

        view.setOnClickListener {
            Snackbar.make(
                parent,
                "Laporan ${data.lokasiTps}: ${data.kendala} (${data.status})",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        return view
    }
}
*/