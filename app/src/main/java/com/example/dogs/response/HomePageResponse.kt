package com.example.dogs.response

import com.example.dogs.model.DogBreed

class HomePageResponse {
    var dogList:List<DogBreed>?=null
    var dogError:Boolean?=null
    var dogLoading:Boolean?=null
    var internetAvaliable:Boolean?=null
}
