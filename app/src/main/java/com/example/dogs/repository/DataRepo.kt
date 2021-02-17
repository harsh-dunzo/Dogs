package com.example.dogs.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dogs.roomdb.DoaHelper
import com.example.dogs.roomdb.DogDao
import com.example.dogs.model.DogBreed
import com.example.dogs.networkcalls.DogApiService
import com.example.dogs.response.HomePageResponse
import com.example.dogs.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback


object DataRepo{

    lateinit var instance:DataRepo;
    private val localDs = LocalDs()
    private var dataavalalbe:Int=0
    val gotResponse= HomePageResponse()
    val response = MutableLiveData<HomePageResponse>()


    fun fetchData() {
        if(gotResponse.internetAvaliable == true){
            fetchFromRemote()
        }else{
            fetchFromDatabase()
        }
    }


    private fun fetchFromRemote() {
       gotResponse.dogLoading=true
        response.postValue(gotResponse)
       remoteDs.DogList()

    }

    private fun fetchFromDatabase() {
        gotResponse.dogLoading=true
        response.postValue(gotResponse)
        CoroutineScope(IO).launch {
            dataavalalbe = localDs.getAllDogCount()
            if (dataavalalbe == 0) {
                gotResponse.dogLoading=false;
                gotResponse.dogError=true;
                response.postValue(gotResponse)
            } else {
                val dogs = localDs.getAllDogs()
                dogsRetrieved(dogs)
            }
        }
    }



    private  fun storeDogsLocally(list: List<DogBreed>) {
        localDs.deleteAllDogs()
        localDs.insetAllDogs(list)
        dogsRetrieved(localDs.getAllDogs())
    }

    private fun dogsRetrieved(list: List<DogBreed>) {
        gotResponse.dogList=list
        gotResponse.dogLoading=false
        gotResponse.dogError=false
        response.postValue(gotResponse)
    }




}