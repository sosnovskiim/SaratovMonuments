package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovmonuments.adapters.MonumentsListAdapter
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentMonumentsBinding
import com.sosnowskydevelop.saratovmonuments.utilities.BUNDLE_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
import com.sosnowskydevelop.saratovmonuments.utilities.InjectorUtils
import com.sosnowskydevelop.saratovmonuments.utilities.REQUEST_KEY_CATEGORY_ID_FROM_CATEGORIES_TO_MONUMENTS
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentsViewModel

class MonumentsFragment : Fragment() {
    private lateinit var binding: FragmentMonumentsBinding

    private val viewModel: MonumentsViewModel by viewModels {
        InjectorUtils.provideMonumentsViewModelFactory(context = requireContext())
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