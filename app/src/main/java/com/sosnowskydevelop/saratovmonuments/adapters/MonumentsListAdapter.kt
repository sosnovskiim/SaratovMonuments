package com.sosnowskydevelop.saratovmonuments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.databinding.ItemMonumentBinding
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentItemViewModel

class MonumentsListAdapter(
    var monuments: Array<Monument>,
    private val fragment: Fragment,
) : RecyclerView.Adapter<MonumentsListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemMonumentBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = ItemMonumentBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.viewModel = MonumentItemViewModel(monument = monuments[position])
        holder.binding.monumentName.setOnClickListener {
            Toast.makeText(
                fragment.requireContext(),
                "Следующий фрагмент пока не готов(",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun getItemCount(): Int = monuments.size
}