package com.example.dogs.roomdb


object DoaHelper{

    private var dogDoa:DogDao = DogDatabase.getDb().dogDoa()

    fun getDoa() : DogDao {
        return dogDoa;
    }

}