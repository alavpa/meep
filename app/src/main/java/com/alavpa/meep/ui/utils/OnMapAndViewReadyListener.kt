package com.alavpa.meep.ui.utils

import android.view.ViewTreeObserver
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

class OnMapAndViewReadyListener(
    private val mapFragment: SupportMapFragment?,
    private val onGlobalLayoutAndMapReadyListener: OnGlobalLayoutAndMapReadyListener
) : ViewTreeObserver.OnGlobalLayoutListener, OnMapReadyCallback {

    private val mapView = mapFragment?.view
    private var isMapReady = false
    private var isViewReady = false
    private var googleMap: GoogleMap? = null

    interface OnGlobalLayoutAndMapReadyListener {
        fun onMapReady(googleMap: GoogleMap?)
    }

    init {
        registerListeners()
    }

    private fun registerListeners() {
        // View layout.
        if (mapView?.width != 0 && mapView?.height != 0) {
            // View has already completed layout.
            isViewReady = true
        } else {
            // Map has not undergone layout, register a View observer.
            mapView.viewTreeObserver.addOnGlobalLayoutListener(this)
        }

        // GoogleMap. Note if the GoogleMap is already ready it will still fire the callback later.
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        // NOTE: The GoogleMap API specifies the listener is removed just prior to invocation.
        this.googleMap = googleMap ?: return
        isMapReady = true
        fireCallbackIfReady()
    }

    override fun onGlobalLayout() {
        mapView?.viewTreeObserver?.removeOnGlobalLayoutListener(this)
        isViewReady = true
        fireCallbackIfReady()
    }

    private fun fireCallbackIfReady() {
        if (isViewReady && isMapReady) {
            onGlobalLayoutAndMapReadyListener.onMapReady(googleMap)
        }
    }
}