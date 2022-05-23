package com.koin.dependencyIntro.data.api

import com.koin.dependencyIntro.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val service: ApiService): ApiHelper
{
    override suspend fun getUser(): Response<List<User>> {
        return service.getUsers()
    }
}