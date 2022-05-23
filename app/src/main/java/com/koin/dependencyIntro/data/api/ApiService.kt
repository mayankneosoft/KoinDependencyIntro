package com.koin.dependencyIntro.data.api

import com.koin.dependencyIntro.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}