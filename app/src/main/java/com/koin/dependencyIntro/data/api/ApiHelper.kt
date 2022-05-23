package com.koin.dependencyIntro.data.api

import com.koin.dependencyIntro.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUser(): Response<List<User>>
}