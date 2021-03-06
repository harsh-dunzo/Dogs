package com.example.dogs.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dogs.model.DogBreed

@Database(entities = arrayOf(DogBreed::class),version = 1)
abstract class DogDatabase: RoomDatabase() {

    abstract fun dogDoa():DogDao

    companion object{

        private lateinit var instance:DogDatabase

        @JvmStatic
        fun initDB(context: Context) {
            if(::instance.isInitialized.not()){
                synchronized(DogDatabase::class.java){
                    instance=Room.databaseBuilder(
                        context,
                        DogDatabase::class.java,
                        "dogbatabase"
                    ).build()
                }
            }
        }

        fun getDb():DogDatabase{
            return instance;
        }

    }

}