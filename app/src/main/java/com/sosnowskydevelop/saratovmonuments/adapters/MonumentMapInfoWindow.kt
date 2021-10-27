package com.sosnowskydevelop.saratovmonuments.adapters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.sosnowskydevelop.saratovmonuments.R
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.infowindow.InfoWindow

class MonumentMapInfoWindow(
    mapView: MapView,
    private val fragment: Fragment,
//    private val monumentName: String,
    private val monumentPointLatitude: Double,
    private val monumentPointLongitude: Double,
) : InfoWindow(R.layout.info_window_monument_map, mapView) {
    override fun onOpen(item: Any?) {
        closeAllInfoWindowsOn(mapView)

//        val monumentMarkerName: TextView = mView.findViewById(R.id.monumentMarkerName)
//        monumentMarkerName.text = monumentName

        val monumentGo: TextView = mView.findViewById(R.id.monumentGo)
        monumentGo.setOnClickListener {
            val monumentUri = Uri.parse(
                "yandexnavi://build_route_on_map?" +
                        "lat_to=${monumentPointLatitude}" +
                        "&lon_to=${monumentPointLongitude}"
            )
            val monumentIntent = Intent(
                Intent.ACTION_VIEW, monumentUri
            )
            try {
                ContextCompat.startActivity(
                    fragment.requireContext(), monumentIntent, null
                )
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    fragment.requireContext(),
                    "Приложение Яндекс.Навигатор не найдено",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onClose() {
        // Not implemented
    }
}