package com.alavpa.meep.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alavpa.meep.R
import com.alavpa.meep.domain.model.MeepResource
import com.alavpa.meep.presentation.main.MainPresenter
import com.alavpa.meep.ui.main.info.InfoBottomSheetDialogFragment
import com.alavpa.meep.ui.main.map.MeepSupportMapFragment
import com.alavpa.meep.ui.utils.MarkerManager
import com.alavpa.meep.ui.utils.MeepClusterManager
import com.alavpa.meep.ui.utils.OnMapAndViewReadyListener
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

    private var infoDialog: InfoBottomSheetDialogFragment? = null

    private var isFirstLoad = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isFirstLoad = savedInstanceState == null

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map)
                as? MeepSupportMapFragment
        OnMapAndViewReadyListener(mapFragment, this)

        infoDialog =
            supportFragmentManager.findFragmentByTag("showInfo") as? InfoBottomSheetDialogFragment

        if (infoDialog != null) infoDialog?.dismiss()
        else infoDialog = InfoBottomSheetDialogFragment.newInstance()

        presenter.populateLiveData.observe(this, Observer(::populate))
        presenter.selectResourceLiveData.observe(this, Observer(::showInfo))
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        initMap(googleMap)
    }

    private fun initMap(googleMap: GoogleMap?) {

        this.googleMap = googleMap
        this.googleMap?.setOnCameraIdleListener(this)
        this.googleMap?.setOnMarkerClickListener {
            presenter.selectResource(it.tag as? MeepResource)
            true
        }

        clusterManager = clusterProvider.provide(this, googleMap)

        if (isFirstLoad) {
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
        }
    }

    private fun populate(resources: List<MeepResource>?) {

        val res = resources ?: listOf()
        googleMap?.clear()
        if (res.size < LIMIT_MARKERS) {
            markerManager.addMarkers(googleMap, res)
        } else {
            clusterManager?.addClusters(res)
        }
    }

    private fun showInfo(resource: MeepResource?) {
        resource?.run {
            googleMap?.clear()
            markerManager.addMarker(googleMap, this)

            infoDialog?.resource = resource
            infoDialog?.onDismissListener =
                object : InfoBottomSheetDialogFragment.OnDismissListener {
                    override fun onDismiss() {
                        googleMap?.run {
                            presenter.onDismissInfo(
                                lowerLeftLat(),
                                lowerLeftLon(),
                                upperRightLat(),
                                upperRightLon()
                            )
                        }
                    }
                }
            infoDialog?.show(supportFragmentManager, "showInfo")
        }
    }

    override fun onCameraIdle() {
        googleMap?.run {
            presenter.getResources(
                lowerLeftLat(),
                lowerLeftLon(),
                upperRightLat(),
                upperRightLon()
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.selectResourceLiveData.removeObservers(this)
        presenter.populateLiveData.removeObservers(this)
    }
}

fun GoogleMap.lowerLeftLat(): Double {
    return projection.visibleRegion.latLngBounds.southwest.latitude
}

fun GoogleMap.lowerLeftLon(): Double {
    return projection.visibleRegion.latLngBounds.southwest.longitude
}

fun GoogleMap.upperRightLat(): Double {
    return projection.visibleRegion.latLngBounds.northeast.latitude
}

fun GoogleMap.upperRightLon(): Double {
    return projection.visibleRegion.latLngBounds.northeast.longitude
}