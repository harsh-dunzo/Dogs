package com.example.dogs.repository

import com.example.dogs.model.DogBreed
import com.example.dogs.networkcalls.DogApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

object RemoteDS {


     suspend fun DogList(): List<DogBreed> {
          return DogApiService().getDogs()
     }



}