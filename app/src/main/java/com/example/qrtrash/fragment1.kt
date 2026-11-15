package com.example.qrtrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qrtrash.databinding.FragmentFragment1Binding
import com.google.android.material.snackbar.Snackbar

class fragment1 : Fragment() {

    private var _binding: FragmentFragment1Binding? = null
    private val binding get() = _binding!!

    // Mengambil data status "Pending" dari listOf terpusat di LaporanModel
    private val dataPending = LaporanModel.semuaLaporan.filter { it.status == "Pending" }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragment1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ================= KODE BARU MODUL 10: RECYCLERVIEW =================
        val laporanAdapter = LaporanAdapter(dataPending) { selectedItem ->
            Snackbar.make(
                binding.root,
                "Laporan ${selectedItem.lokasiTps}: ${selectedItem.kendala} (${selectedItem.status})",
                Snackbar.LENGTH_SHORT
            ).show()
        }

        binding.rvLaporanItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = laporanAdapter
        }

        /*
        // ================= KODE LAMA MODUL 7: LISTVIEW (DIKOMENTARI) =================
        val adapterLama = LaporanAdapter(requireContext(), dataPending)
        binding.listLaporanItems.adapter = adapterLama
        */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}