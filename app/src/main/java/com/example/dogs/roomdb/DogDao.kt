package com.example.dogs.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dogs.model.DogBreed

@Dao
interface DogDao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertAll(dogs:List<DogBreed>):List<Long>

    @Query("Select * from dogbreed")
    suspend fun getAllDogs():List<DogBreed>

    @Query("Select Count(*) from dogbreed")
    suspend fun getAllDogCount():Int

    @Query("Select * from dogbreed where uuid =:dogId")
    suspend fun getDog(dogId:Int):DogBreed

    @Query("Delete from dogbreed")
    suspend fun deleteAllDogs()
}