package com.sosnowskydevelop.saratovmonuments.adapters

import android.widget.TextView
import com.sosnowskydevelop.saratovmonuments.R
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow

class MonumentMapInfoWindow(
    mapView: MapView,
    private val monumentName: String,
) : InfoWindow(R.layout.info_window_monument_map, mapView) {
    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)

        val monumentMarkerName: TextView = mView.findViewById(R.id.monumentMarkerName)
        monumentMarkerName.text = monumentName
    }

    override fun onClose() {
        // Not implemented
    }
}