package com.example.qrtrash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.qrtrash.data.AppDatabase
import com.example.qrtrash.data.entity.LaporanEntity
import com.example.qrtrash.databinding.FragmentFragment3Binding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class fragment3 : Fragment() {

    private var _binding: FragmentFragment3Binding? = null
    private val binding get() = _binding!!

    private lateinit var db: AppDatabase
    private lateinit var laporanAdapter: LaporanAdapter
    private val listLaporan = mutableListOf<LaporanEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragment3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())
        laporanAdapter = LaporanAdapter(
            laporanList = listLaporan,
            onItemClick = { selectedItem ->
                Snackbar.make(
                    binding.root,
                    "${selectedItem.idTps}: ${selectedItem.kategoriMasalah} (${selectedItem.koordinatGps})",
                    Snackbar.LENGTH_SHORT
                ).show()
            },
            onDelete = { laporan ->
                deleteLaporan(laporan)
            }
        )

        binding.rvLaporanItems.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = laporanAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        if (_binding != null) fetchLaporan()
    }

    private fun fetchLaporan() {
        viewLifecycleOwner.lifecycleScope.launch {
            val result = db.laporanDao().getByStatus("Selesai")
            listLaporan.clear()
            listLaporan.addAll(result)
            laporanAdapter.notifyDataSetChanged()
        }
    }

    private fun deleteLaporan(laporan: LaporanEntity) {
        viewLifecycleOwner.lifecycleScope.launch {
            db.laporanDao().delete(laporan)
            fetchLaporan()
        }
    }

    override fun onDestroyView() {
        binding.rvLaporanItems.adapter = null
        _binding = null
        super.onDestroyView()
    }
}