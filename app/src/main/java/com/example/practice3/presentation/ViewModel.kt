package com.example.practice3.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context.LOCATION_SERVICE
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practice3.domain.model.EntriesList
import com.example.practice3.domain.repository.Repository
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val application: Application
, private val repository: Repository): ViewModel() {

    private var _entriesList: MutableLiveData<EntriesList> = MutableLiveData()
    val entriesList: LiveData<EntriesList> = _entriesList
    private val currentPage = 0
    val locationManager: LocationManager? = application.getSystemService(LOCATION_SERVICE) as LocationManager?

    //@SuppressLint("MissingPermission")
    fun fetch(query1: String){
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)
        fusedLocationClient.lastLocation.addOnSuccessListener {location->
            if(location  != null) {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = repository.fetchEntryList(query1, location.altitude.toString(), query1, query1)
                    if (result != null)
                        _entriesList.postValue(result)
                }
            }
        }
    }

}