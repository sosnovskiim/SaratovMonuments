package com.sosnowskydevelop.saratovmonuments.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sosnowskydevelop.saratovmonuments.R
import com.sosnowskydevelop.saratovmonuments.adapters.MonumentsListAdapter
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentMonumentSearchBinding
import com.sosnowskydevelop.saratovmonuments.utilities.BUNDLE_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_SEARCH
import com.sosnowskydevelop.saratovmonuments.utilities.InjectorUtils
import com.sosnowskydevelop.saratovmonuments.utilities.REQUEST_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_SEARCH
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentSearchViewModel

class MonumentSearchFragment : Fragment() {
    private lateinit var binding: FragmentMonumentSearchBinding

    private val viewModel: MonumentSearchViewModel by viewModels {
        InjectorUtils.provideMonumentSearchViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonumentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var listAdapter: MonumentsListAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setActionBarTitle()

        binding.monuments.layoutManager = LinearLayoutManager(requireContext())

        listAdapter = MonumentsListAdapter(
            monuments = arrayOf(),
            fragment = this,
        )
        binding.monuments.adapter = listAdapter

        binding.monumentSearch.addTextChangedListener {
            val searchRequest: String = it.toString()
            if (searchRequest.isNotBlank()) {
                viewModel.updateMonuments(searchRequest = searchRequest)
                if (viewModel.monuments.isNotEmpty()) {
                    listAdapter.monuments = viewModel.monuments
                    listAdapter.notifyDataSetChanged()
                    binding.nothingFound.visibility = View.INVISIBLE
                    binding.monuments.visibility = View.VISIBLE
                } else {
                    binding.monuments.visibility = View.INVISIBLE
                    binding.nothingFound.visibility = View.VISIBLE
                }
            } else {
                binding.monuments.visibility = View.INVISIBLE
                binding.nothingFound.visibility = View.VISIBLE
            }
        }

        setFragmentResultListener(
            requestKey = REQUEST_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_SEARCH
        ) { _, bundle ->
            viewModel.initData(
                categoryId = bundle.getLong(
                    BUNDLE_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_SEARCH
                )
            )
        }
    }

    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            resources.getString(R.string.action_bar_title_monument_search)
    }
}