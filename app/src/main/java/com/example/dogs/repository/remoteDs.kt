package com.example.dogs.repository

import com.example.dogs.model.DogBreed
import com.example.dogs.networkcalls.DogApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object remoteDs {


     fun DogList(){
          DogApiService().getDogs().enqueue(object :retrofit2.Callback<List<DogBreed>>{
               override fun onResponse(
                    call: Call<List<DogBreed>>,
                    response: Response<List<DogBreed>>
               ) {
                    DataRepo.gotResponse.dogList=response.body()
                    DataRepo.gotResponse.dogLoading=false
                    DataRepo.response.postValue(DataRepo.gotResponse)

               }

               override fun onFailure(call: Call<List<DogBreed>>, t: Throwable) {
               }


          })

     }



}