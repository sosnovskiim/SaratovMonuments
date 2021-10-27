package com.sosnowskydevelop.saratovmonuments.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.sosnowskydevelop.saratovmonuments.adapters.MonumentMapInfoWindow
import com.sosnowskydevelop.saratovmonuments.databinding.FragmentMonumentMapBinding
import com.sosnowskydevelop.saratovmonuments.utilities.BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_MAP
import com.sosnowskydevelop.saratovmonuments.utilities.InjectorUtils
import com.sosnowskydevelop.saratovmonuments.utilities.REQUEST_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_MAP
import com.sosnowskydevelop.saratovmonuments.viewmodels.MonumentMapViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.events.MapEventsReceiver
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.MapEventsOverlay
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.infowindow.InfoWindow

class MonumentMapFragment : Fragment(), MapEventsReceiver {
    private lateinit var binding: FragmentMonumentMapBinding

    private val viewModel: MonumentMapViewModel by viewModels {
        InjectorUtils.provideMonumentMapViewModelFactory(context = requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMonumentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFragmentResultListener(
            requestKey = REQUEST_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_MAP
        ) { _, bundle ->
            viewModel.initData(
                monumentId = bundle.getLong(
                    BUNDLE_KEY_MONUMENT_ID_FROM_MONUMENT_PRIMARY_TO_MONUMENT_MAP
                )
            )

            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                viewModel.monumentName

            initMapView()
        }
    }

    private fun initMapView() {
        initPreferences()

        binding.monumentMapView.overlays.add(index = 0, MapEventsOverlay(this))
        binding.monumentMapView.setTileSource(TileSourceFactory.MAPNIK)
        binding.monumentMapView.setBuiltInZoomControls(true)
        binding.monumentMapView.setMultiTouchControls(true)
        binding.monumentMapView.controller.setZoom(15.0)

        val monumentGeoPoint =
            GeoPoint(viewModel.monumentPointLatitude!!, viewModel.monumentPointLongitude!!)
        binding.monumentMapView.controller.setCenter(monumentGeoPoint)

        val monumentMarker = Marker(binding.monumentMapView)
        monumentMarker.position = monumentGeoPoint
        monumentMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        monumentMarker.infoWindow = MonumentMapInfoWindow(
            mapView = binding.monumentMapView,
            fragment = this,
            monumentPointLatitude = viewModel.monumentPointLatitude!!,
            monumentPointLongitude = viewModel.monumentPointLongitude!!,
        )
        binding.monumentMapView.overlays.add(monumentMarker)

        binding.monumentMapView.invalidate()
    }

    private fun initPreferences() {
        @Suppress("DEPRECATION")
        Configuration.getInstance().load(
            requireActivity().applicationContext, PreferenceManager.getDefaultSharedPreferences(
                requireActivity().applicationContext
            )
        )
    }

    override fun onResume() {
        super.onResume()
        binding.monumentMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.monumentMapView.onPause()
    }

    override fun singleTapConfirmedHelper(p: GeoPoint?): Boolean {
        InfoWindow.closeAllInfoWindowsOn(binding.monumentMapView)
        return true
    }

    override fun longPressHelper(p: GeoPoint?): Boolean {
        return false
    }
}