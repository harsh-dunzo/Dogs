package com.example.dogs.view.dogList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.repository.DataRepo
import com.example.dogs.view.dogList.ListFragment
import com.example.dogs.view.doglist.inter.NetworkAvaliable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListViewModel() : ViewModel() {


    private val dogobj= DataRepo.response()

    fun getDogobj()=dogobj

    fun getData(networkAvaliable: NetworkAvaliable) {
        DataRepo.getResponse().internetAvaliable=networkAvaliable.checkNetwork()
        viewModelScope.launch {
            DataRepo.fetchData()
        }
    }
}


