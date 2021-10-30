package com.sosnowskydevelop.saratovmonuments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sosnowskydevelop.saratovmonuments.R
import com.sosnowskydevelop.saratovmonuments.data.Monument
import com.sosnowskydevelop.saratovmonuments.databinding.ItemMonumentBinding
import com.sosnowskydevelop.saratovmonuments.utilities.*
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
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY,
                result = bundleOf(
                    BUNDLE_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
                            to monuments[position].categoryId
                )
            )
            fragment.setFragmentResult(
                requestKey = REQUEST_KEY_MONUMENT_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY,
                result = bundleOf(
                    BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
                            to monuments[position].id
                )
            )
            try {
                fragment.findNavController()
                    .navigate(R.id.action_from_monumentsFragment_to_monumentPrimaryFragment)
            } catch (e: IllegalArgumentException) {
                fragment.findNavController()
                    .navigate(R.id.action_from_monumentSearchFragment_to_monumentPrimaryFragment)
            }
        }
    }

    override fun getItemCount(): Int = monuments.size
}