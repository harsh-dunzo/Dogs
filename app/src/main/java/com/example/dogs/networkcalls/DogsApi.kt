package com.example.dogs.networkcalls

import com.example.dogs.model.DogBreed
import retrofit2.Call
import retrofit2.http.GET

interface DogsApi {

    @GET("")
     suspend fun getDogs(): List<DogBreed>
}