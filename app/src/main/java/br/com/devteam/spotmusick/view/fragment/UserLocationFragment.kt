package br.com.devteam.spotmusick.view.fragment

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import br.com.devteam.spotmusick.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UserLocationFragment : Fragment() {

    private var gMap: GoogleMap? = null
    private var userMarker: Marker? = null
    private val ACCESS_FINE_LOCATION = 99
    private val ACCESS_COARSE_LOCATION = 88
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    private val callback = OnMapReadyCallback { googleMap ->
        gMap = googleMap
        val sydney = LatLng(-34.0, 151.0)

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        val view = inflater.inflate(R.layout.fragment_user_location, container, false)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab_user_location)
        fab.setOnClickListener {
            checkPermissionUserLocation()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun checkPermissionUserLocation() {

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
//            ActivityCompat.checkSelfPermission(
//                requireContext(),
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
            val permissions = arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION)
            ActivityCompat.requestPermissions(
                requireActivity(),
                permissions,
                ACCESS_COARSE_LOCATION
            )
            return
        }
        moveMapToUserLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESS_FINE_LOCATION) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                moveMapToUserLocation()
            }
        }
    }

    fun moveMapToUserLocation() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    val userLatLng = LatLng(location!!.latitude, location!!.longitude)
                    gMap!!.moveCamera(
                        CameraUpdateFactory.newLatLngZoom(
                            userLatLng,
                            15F
                        )
                    )
                    if (userMarker == null) {
                        userMarker = gMap!!.addMarker(
                            MarkerOptions().position(userLatLng).title("Sua posição!")
                        )
                    }
                }
        }
    }
}