package com.koin.dependencyIntro.module

import com.koin.dependencyIntro.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }

}