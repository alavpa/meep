package com.alavpa.meep.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alavpa.meep.R
import com.alavpa.meep.presentation.main.MainPresenter
import com.alavpa.meep.ui.utils.MarkerManager
import com.alavpa.meep.ui.utils.MeepClusterManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : OnMapAndViewReadyListener.OnGlobalLayoutAndMapReadyListener,
    GoogleMap.OnCameraIdleListener, AppCompatActivity() {

    companion object {
        private const val INITIAL_LOWER_LEFT_LAT = 38.711046
        private const val INITIAL_LOWER_LEFT_LON = -9.160096
        private const val INITIAL_UPPER_RIGHT_LAT = 38.739429
        private const val INITIAL_UPPER_RIGHT_LON = -9.137115
        private const val PADDING = 0
        private const val LIMIT_MARKERS = 100
    }

    private val presenter: MainPresenter by viewModel()
    private val markerManager: MarkerManager by inject()
    private val clusterProvider: MeepClusterManager.Provider by inject()
    private var clusterManager: MeepClusterManager? = null
    private var googleMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as? MeepSupportMapFragment
        OnMapAndViewReadyListener(mapFragment, this)

        presenter.renderLiveData.observe(this, Observer(::render))
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        initMap(googleMap)
    }

    private fun initMap(googleMap: GoogleMap?) {

        this.googleMap = googleMap
        this.googleMap?.setOnCameraIdleListener(this)
        this.googleMap?.setOnMarkerClickListener {
            it.showInfoWindow()
            true
        }

        clusterManager = clusterProvider.provide(this, googleMap)

        val lisbon = LatLngBounds.builder()
            .include(LatLng(INITIAL_LOWER_LEFT_LAT, INITIAL_LOWER_LEFT_LON))
            .include(LatLng(INITIAL_UPPER_RIGHT_LAT, INITIAL_UPPER_RIGHT_LON))
            .build()

        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngBounds(
                lisbon,
                PADDING
            )
        )

        presenter.getResources(
            INITIAL_LOWER_LEFT_LAT,
            INITIAL_LOWER_LEFT_LON,
            INITIAL_UPPER_RIGHT_LAT,
            INITIAL_UPPER_RIGHT_LON
        )
    }

    private fun render(viewModel: MainPresenter.ViewModel) {
        googleMap?.clear()

        if (viewModel.resources.size < LIMIT_MARKERS) {
            viewModel.resources.forEach {
                markerManager.addMarker(googleMap, it)
            }
        } else {
            clusterManager?.clearItems()
            viewModel.resources.forEach {
                clusterManager?.addItem(MeepClusterManager.MeepClusterItem(LatLng(it.y, it.x)))
            }
            clusterManager?.cluster()
        }
    }

    override fun onCameraIdle() {
        googleMap?.run {
            val lowerLeft = projection.visibleRegion.latLngBounds.southwest
            val upperRight = projection.visibleRegion.latLngBounds.northeast
            presenter.getResources(
                lowerLeft.latitude,
                lowerLeft.longitude,
                upperRight.latitude,
                upperRight.longitude
            )
        }
    }
}