package com.koin.dependencyIntro.data.respository

import com.koin.dependencyIntro.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUser() = apiHelper.getUser()
}