package com.example.dogs.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogs.model.DogBreed

@Dao
interface DogDao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
     fun insertAll(dogs:List<DogBreed>):List<Long>

    @Query("Select * from dogbreed")
     fun getAllDogs():List<DogBreed>

    @Query("Select Count(*) from dogbreed")
     fun getAllDogCount():Int

    @Query("Select * from dogbreed where uuid =:dogId")
     fun getDog(dogId:Int):DogBreed

    @Query("Delete from dogbreed")
     fun deleteAllDogs()
}