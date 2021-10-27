package com.sosnowskydevelop.saratovmonuments.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sosnowskydevelop.saratovmonuments.R
import com.sosnowskydevelop.saratovmonuments.activities.ImageActivity
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

            setActionBarTitle() // First opening of the fragment.
        }

        setActionBarTitle() // Return to the fragment.

        setFragmentResultListener(
            requestKey = REQUEST_KEY_MONUMENT_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
        ) { _, bundle ->
            viewModel.initData(
                categoryId = null,
                monumentId = bundle.getLong(
                    BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENTS_TO_MONUMENT_PRIMARY
                ),
            )

            // First opening of the fragment.
            setMonumentPhoto()
            setMonumentName()
        }

        // Return to the fragment.
        setMonumentPhoto()
        setMonumentName()

        binding.monumentMap.setOnClickListener {
            setFragmentResult(
                requestKey = REQUEST_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_MAP,
                result = bundleOf(
                    BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_MAP
                            to viewModel.monumentId
                )
            )
            findNavController()
                .navigate(R.id.action_from_monumentPrimaryFragment_to_monumentMapFragment)
        }
    }

    private fun setActionBarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            viewModel.categoryName
    }

    private fun setMonumentPhoto() {
        if (viewModel.monumentPhotoName != null) {
            val monumentImageResId: Int = resources.getIdentifier(
                viewModel.monumentPhotoName,
                "drawable",
                "com.sosnowskydevelop.saratovmonuments"
            )
            binding.monumentPhoto.setImageResource(monumentImageResId)
            binding.monumentPhoto.setOnClickListener {
                val intent = Intent(requireActivity().applicationContext, ImageActivity::class.java)
                intent.putExtra("imageId", monumentImageResId)
                intent.putExtra("title", viewModel.monumentName)
                startActivity(intent)
            }
        }
    }

    private fun setMonumentName() {
        binding.monumentName.text = viewModel.monumentName
    }
}