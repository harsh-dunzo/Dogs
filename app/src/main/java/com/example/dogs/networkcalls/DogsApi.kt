package com.example.dogs.networkcalls

import com.example.dogs.model.DogBreed
import retrofit2.Call
import retrofit2.http.GET

interface DogsApi {

    @GET("DevTides/DogsApi/master/dogs.json")
     fun getDogs(): Call<List<DogBreed>>
}