package com.example.dogs.view.detaildogs.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.repository.DataRepo
import com.example.dogs.roomdb.DogDatabase
import com.example.dogs.model.DogBreed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailViewModel():ViewModel() {

    var dogDetails=MutableLiveData<DogBreed>()
    private val DogRepo = DataRepo()

    fun fetch(uuid: Int){
        CoroutineScope(IO).launch{
            val dogBreed = DogRepo.getDog(uuid)
            dogDetails.postValue(dogBreed)
        }
    }
}