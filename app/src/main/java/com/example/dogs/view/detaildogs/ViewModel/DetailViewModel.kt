package com.example.dogs.view.detaildogs.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogs.model.DogBreed
import com.example.dogs.repository.LocalDS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailViewModel():ViewModel() {

    var dogDetails=MutableLiveData<DogBreed>()

    fun fetch(uuid: Int){
        CoroutineScope(IO).launch{
            val dogBreed = LocalDS().getDog(uuid)
            dogDetails.postValue(dogBreed)
        }
    }
}