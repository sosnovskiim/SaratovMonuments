package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentMonumentDescriptionBinding
import com.sosnowskydevelop.saratovmonuments.utilities.BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_DESCRIPTION
import com.sosnowskydevelop.saratovmonuments.utilities.InjectorUtils
import com.sosnowskydevelop.saratovmonuments.utilities.REQUEST_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_DESCRIPTION
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentDescriptionViewModel

class MonumentDescriptionFragment : Fragment() {
    private lateinit var binding: FragmentMonumentDescriptionBinding

    private val viewModel: MonumentDescriptionViewModel by viewModels {
        InjectorUtils.provideMonumentDescriptionViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonumentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_DESCRIPTION
        ) { _, bundle ->
            viewModel.initData(
                monumentId = bundle.getLong(
                    BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_DESCRIPTION
                )
            )

            setActionBarTitle()
            setMonumentInstallationDate()
            setMonumentAuthors()
            setMonumentDescription()
            setMonumentLinks()
        }
    }

    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            viewModel.monumentName
    }

    private fun setMonumentInstallationDate() {
        binding.monumentInstallationDate.text = viewModel.monumentInstallationDate
    }

    private fun setMonumentAuthors() {
        binding.monumentAuthors.text = viewModel.monumentAuthors
    }

    private fun setMonumentDescription() {
        binding.monumentDescription.text = viewModel.monumentDescription
    }

    private fun setMonumentLinks() {
        binding.monumentLinks.text = viewModel.monumentLinks
    }
}