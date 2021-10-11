package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentMonumentPrimaryBinding
import com.sosnowskydevelop.saratovmonuments.utilities.*
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentPrimaryViewModel

class MonumentPrimaryFragment : Fragment() {
    private lateinit var binding: FragmentMonumentPrimaryBinding

    private val viewModel: MonumentPrimaryViewModel by viewModels {
        InjectorUtils.provideMonumentPrimaryViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonumentPrimaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
        ) { _, bundle ->
            viewModel.initData(
                categoryId = bundle.getLong(
                    BUNDLE_KEY_CATEGORY_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
                ),
                monumentId = null,
            )

            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                viewModel.categoryName
        }

        setFragmentResultListener(
            requestKey = REQUEST_KEY_MONUMENT_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
        ) { _, bundle ->
            viewModel.initData(
                categoryId = null,
                monumentId = bundle.getLong(
                    BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
                ),
            )

            binding.monumentImage.setImageResource(resources.getIdentifier(
                viewModel.monumentPhotoName,
                "drawable",
                "com.sosnowskydevelop.saratovmonuments"
            ))

            binding.monumentName.text = viewModel.monumentName
        }
    }
}