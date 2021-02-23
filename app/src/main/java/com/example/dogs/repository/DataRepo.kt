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
    private val homepageResponseState = HomePageResponse()
    private val responseLiveData = MutableLiveData<HomePageResponse>()

    fun changeState(res:HomePageResponse){
        responseLiveData.postValue(res)
    }

    fun getResponse() = homepageResponseState
    fun response()= responseLiveData

    fun fetchData() {
        if (homepageResponseState.internetAvaliable == true) {
            fetchFromRemote()
        } else {
            fetchFromDatabase()
        }
    }

    private fun fetchFromRemote() {
        homepageResponseState.dogLoading = true
        changeState(homepageResponseState)
        CoroutineScope(IO).launch {
            homepageResponseState.dogList = RemoteDS.dogList()
            homepageResponseState.dogList?.let { storeDogsLocally(it) }
        }
    }


    private fun fetchFromDatabase() {
        homepageResponseState.dogLoading = true
        changeState(homepageResponseState)
        CoroutineScope(IO).launch {
            dataavalalbe = localDs.getAllDogCount()
            if (dataavalalbe == 0) {
                homepageResponseState.dogLoading = false;
                homepageResponseState.dogError = true;
                changeState(homepageResponseState)
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
        homepageResponseState.dogList = list
        homepageResponseState.dogLoading = false
        homepageResponseState.dogError = false
        changeState(homepageResponseState)
    }


}