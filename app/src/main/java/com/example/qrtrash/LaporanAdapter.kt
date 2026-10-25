package com.example.qrtrash

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.qrtrash.databinding.ItemLaporanBinding
import com.google.android.material.snackbar.Snackbar

class LaporanAdapter(
    context: Context,
    private val laporanList: List<LaporanModel>
) : ArrayAdapter<LaporanModel>(context, 0, laporanList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemLaporanBinding.inflate(LayoutInflater.from(context), parent, false)
        val view = binding.root

        val data = laporanList[position]

        // 1. Muat gambar dari URL ke ImageView menggunakan Glide (Modul 7 Tahap 4.6)
        Glide.with(context)
            .load(data.imageUrl)
            .placeholder(R.drawable.logoaplikasi)
            .error(R.drawable.logoaplikasi)
            .into(binding.avatarImg)

        // 2. Isi teks pada setiap komponen item
        binding.textSender.text = data.lokasiTps
        binding.textMessage.text = data.kendala
        binding.textTime.text = data.waktu
        binding.tvStatusBadge.text = data.status

        // Sesuaikan warna badge berdasarkan status laporan
        when (data.status) {
            "Pending" -> {
                binding.tvStatusBadge.setBackgroundColor(Color.parseColor("#FEF3C7"))
                binding.tvStatusBadge.setTextColor(Color.parseColor("#D97706"))
            }
            "Diterima" -> {
                binding.tvStatusBadge.setBackgroundColor(Color.parseColor("#E0F2FE"))
                binding.tvStatusBadge.setTextColor(Color.parseColor("#0284C7"))
            }
            "Selesai" -> {
                binding.tvStatusBadge.setBackgroundColor(Color.parseColor("#DCFCE7"))
                binding.tvStatusBadge.setTextColor(Color.parseColor("#16A34A"))
            }
        }

        // 3. Aksi klik pada setiap item memunculkan Snackbar (Modul 7 Tahap 4.8)
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