package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovmonuments.R
import com.sosnowskydevelop.saratovmonuments.adapters.MonumentsListAdapter
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentMonumentsBinding
import com.sosnowskydevelop.saratovmonuments.utilities.*
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentsViewModel

class MonumentsFragment : Fragment() {
    private lateinit var binding: FragmentMonumentsBinding

    private val viewModel: MonumentsViewModel by viewModels {
        InjectorUtils.provideMonumentsViewModelFactory(context = requireContext())
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
                setFragmentResult(
                    requestKey = REQUEST_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_SEARCH,
                    result = bundleOf(
                        BUNDLE_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_SEARCH
                                to viewModel.categoryId
                    )
                )
                findNavController().navigate(R.id.action_from_monumentsFragment_to_monumentSearchFragment)
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
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonumentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.monuments.layoutManager = LinearLayoutManager(requireContext())

        val listAdapter = MonumentsListAdapter(
            monuments = viewModel.monuments,
            fragment = this,
        )
        binding.monuments.adapter = listAdapter

        setFragmentResultListener(
            requestKey = REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
        ) { _, bundle ->
            viewModel.initData(
                categoryId = bundle.getLong(
                    BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
                )
            )

            setActionBarTitle() // First opening of the fragment.

            listAdapter.monuments = viewModel.monuments
        }

        setActionBarTitle() // Return to the fragment.
    }

    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            viewModel.categoryName
    }
}