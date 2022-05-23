package com.koin.dependencyIntro.module

import com.koin.dependencyIntro.data.respository.MainRepository
import org.koin.dsl.module


val repositoryModule = module{
    single {
        MainRepository(get())
    }
}