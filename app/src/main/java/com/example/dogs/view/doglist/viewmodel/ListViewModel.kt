package com.example.dogs.view.dogList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.repository.DataRepo
import com.example.dogs.interfaces.NetworkAvaliable
import kotlinx.coroutines.launch

class ListViewModel() : ViewModel() {


    private val dogobj= DataRepo.response()

    fun getDogobj()=dogobj

    fun getData(networkAvaliable: NetworkAvaliable) {
        DataRepo.getResponse().internetAvaliable=networkAvaliable.checkNetwork()
        viewModelScope.launch {
            if(DataRepo.getResponse().dogList==null)
                DataRepo.fetchData()
        }
    }
}


