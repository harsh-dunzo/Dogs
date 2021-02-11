package com.example.dogs.repository

import com.example.dogs.RoomDb.DoaHelper
import com.example.dogs.roomdb.DogDao
import com.example.dogs.roomdb.DogDatabase
import com.example.dogs.model.DogBreed


class DataRepo(){

    private var dogDao:DogDao=DoaHelper.getDoa()


    suspend fun getAllDogCount(): Int {
        return dogDao.getAllDogCount()
    }

    suspend fun deleteAllDogs(){
        dogDao.deleteAllDogs()
    }

    suspend fun insetAllDogs(list:List<DogBreed>) {
        dogDao.insertAll(*list.toTypedArray())
    }

    suspend fun getAllDogs(): List<DogBreed> {
        val result=dogDao.getAllDogs()
        return result
    }

    suspend fun getDog(uuid:Int):DogBreed = dogDao.getDog(uuid)


}