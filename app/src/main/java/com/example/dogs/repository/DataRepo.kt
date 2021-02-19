package com.example.dogs.repository

import androidx.lifecycle.MutableLiveData
import com.example.dogs.model.DogBreed
import com.example.dogs.response.HomePageResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


object DataRepo {

    private val localDs = LocalDS()
    private var dataavalalbe: Int = 0
    private val gotResponse = HomePageResponse()
    private val response = MutableLiveData<HomePageResponse>()

    fun changeState(res:HomePageResponse){
        response.postValue(res)
    }

    fun getResponse() = gotResponse
    fun response()= response

    fun fetchData() {
        if (gotResponse.internetAvaliable == true) {
            fetchFromRemote()
        } else {
            fetchFromDatabase()
        }
    }


    private fun fetchFromRemote() {
        gotResponse.dogLoading = true
        changeState(gotResponse)
        RemoteDS.DogList()
        gotResponse.dogList?.let { storeDogsLocally(it) }

    }

    private fun fetchFromDatabase() {
        gotResponse.dogLoading = true
        changeState(gotResponse)
        CoroutineScope(IO).launch {
            dataavalalbe = localDs.getAllDogCount()
            if (dataavalalbe == 0) {
                gotResponse.dogLoading = false;
                gotResponse.dogError = true;
                changeState(gotResponse)
            } else {
                val dogs = localDs.getAllDogs()
                dogsRetrieved(dogs)
            }
        }
    }


    private fun storeDogsLocally(list: List<DogBreed>) {
        CoroutineScope(IO).launch {
            localDs.deleteAllDogs()
            localDs.insetAllDogs(list)
            dogsRetrieved(localDs.getAllDogs())
        }
    }

    private fun dogsRetrieved(list: List<DogBreed>) {
        gotResponse.dogList = list
        gotResponse.dogLoading = false
        gotResponse.dogError = false
        changeState(gotResponse)
    }


}