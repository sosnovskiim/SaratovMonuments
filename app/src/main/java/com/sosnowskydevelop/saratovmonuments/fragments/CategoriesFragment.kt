package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_monument_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                findNavController().navigate(R.id.action_from_categoriesFragment_to_monumentSearchFragment)
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
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

        setActionBarTitle()

        binding.categories.layoutManager = LinearLayoutManager(requireContext())
        binding.categories.adapter = CategoriesListAdapter(
            categories = viewModel.categories,
            fragment = this,
        )
    }

    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.action_bar_title_categories)
    }
}