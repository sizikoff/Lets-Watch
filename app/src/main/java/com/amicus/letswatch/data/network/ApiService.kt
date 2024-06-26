package com.amicus.letswatch.data.network

import com.amicus.letswatch.data.models.MoviesItem
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET("/shows")
    suspend fun getAllMovies(): Response<List<MoviesItem>>
}