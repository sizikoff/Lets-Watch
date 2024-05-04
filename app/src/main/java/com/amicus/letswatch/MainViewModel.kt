package com.amicus.letswatch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amicus.letswatch.data.models.MoviesItem
import com.amicus.letswatch.data.network.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(private val repository: ApiRepository):ViewModel(){

    private val _allmovies = MutableLiveData<List<MoviesItem>>()
    val allmovies:LiveData<List<MoviesItem>>
        get() = _allmovies

    fun getAllMovies(){

        viewModelScope.launch {
            repository.getAllMovies().let {
                if (it.isSuccessful) {
                    _allmovies.postValue(it.body())
                }else{
                    Log.d("check","Failed to load movies : ${it.errorBody()}")
                }
            }
        }
    }
}