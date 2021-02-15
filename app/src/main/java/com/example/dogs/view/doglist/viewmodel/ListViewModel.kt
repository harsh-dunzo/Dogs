package com.example.dogs.view.dogList.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.networkcalls.DogApiService
import com.example.dogs.repository.DataRepo
import com.example.dogs.model.DogBreed
import com.example.dogs.view.doglist.interfaces.NetworkAvaliable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ListViewModel(): ViewModel() {

    private val dogRepo = DataRepo()
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val internetAval= MutableLiveData<Boolean>()
    val dataavalalbe = MutableLiveData<Int>()


    fun refresh(networkAvaliable: NetworkAvaliable) {
        loading.postValue(true)
        CoroutineScope(IO).launch {
            if(networkAvaliable.checkNetwork()){
                fetchFromRemote()
            }
            else{
                fetchFromDatabase()
            }
        }
    }

    private suspend fun fetchFromRemote() {
        Log.d("ListView","Intenet Avalable")
        storeDogsLocally(DogApiService.getDogs())

    }

    private suspend fun fetchFromDatabase() {
        loading.postValue( true)
            dataavalalbe.postValue(dogRepo.getAllDogCount())
            if(dataavalalbe.value==0){
                loading.postValue(false)
                dogsLoadError.postValue(true)
            }else {
                val dogs = dogRepo.getAllDogs()
                dogsRetrieved(dogs)
            }
    }



  private suspend fun storeDogsLocally(list: List<DogBreed>) {
            dogRepo.deleteAllDogs()
            dogRepo.insetAllDogs(list)
            dogsRetrieved(dogRepo.getAllDogs())

    }


    private fun dogsRetrieved(list: List<DogBreed>) {
        dogs.postValue(list)
        dogsLoadError.postValue(false)
        loading.postValue(false)
    }



}


