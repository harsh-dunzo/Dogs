package com.example.dogs.RoomDb

import com.example.dogs.Util.App
import com.example.dogs.roomdb.DogDao
import com.example.dogs.roomdb.DogDatabase


object DoaHelper{

    private var DogDao:DogDao = DogDatabase.getDb().DogDao()

    fun getDoa() : DogDao {
        return DogDao;
    }

}