package com.mpcreative.demo

import android.location.Location
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener,
    GoogleMap.OnMyLocationClickListener {
    lateinit var mMap: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        googleMap.isMyLocationEnabled = true
        // Add a marker in Sydney and move the camera
        // Add a marker in Sydney and move the camera
        val TutorialsPoint = LatLng(23.047600, 72.504060)
       // mMap.uiSettings.isZoomControlsEnabled = true
       // mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isMyLocationButtonEnabled = true
        mMap.addMarker(MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint))


    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false
    }

    override fun onMyLocationClick(location: Location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();

    }
}
