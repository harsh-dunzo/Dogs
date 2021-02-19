package com.example.dogs.view.dogList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogs.repository.DataRepo
import com.example.dogs.view.dogList.ListFragment
import kotlinx.coroutines.launch

class ListViewModel() : ViewModel() {


    private val dogobj= DataRepo.response()

    fun getDogobj()=dogobj

    fun getData(listFragment: ListFragment) {

        DataRepo.getResponse().internetAvaliable=listFragment.checkNetwork()
        viewModelScope.launch {
            DataRepo.fetchData()
        }
    }
}


