package com.example.dogs.repository

import com.example.dogs.model.DogBreed
import com.example.dogs.roomdb.DoaHelper
import com.example.dogs.roomdb.DogDao

class LocalDS {

    private var dogDao: DogDao = DoaHelper.getDoa()

     fun getAllDogCount(): Int {
        return dogDao.getAllDogCount()
    }

     fun deleteAllDogs(){
        dogDao.deleteAllDogs()
    }

     fun insetAllDogs(list:List<DogBreed>) {
        dogDao.insertAll(list)
    }

     fun getAllDogs(): List<DogBreed> {
        val result=dogDao.getAllDogs()
        return result
    }

     fun getDog(uuid:Int): DogBreed = dogDao.getDog(uuid)
}