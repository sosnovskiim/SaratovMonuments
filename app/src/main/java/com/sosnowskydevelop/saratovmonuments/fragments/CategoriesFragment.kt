package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovmonuments.R
import com.sosnowskydevelop.saratovmonuments.adapters.CategoriesListAdapter
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentCategoriesBinding
import com.sosnowskydevelop.saratovmonuments.utilities.InjectorUtils
import com.sosnowskydevelop.saratovmonuments.viewmodels.CategoriesViewModel

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding

    private val viewModel: CategoriesViewModel by viewModels {
        InjectorUtils.provideCategoriesViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.action_bar_title_categories)

        binding.categories.layoutManager = LinearLayoutManager(requireContext())
        binding.categories.adapter = CategoriesListAdapter(
            categories = viewModel.categories,
            fragment = this,
        )
    }
}