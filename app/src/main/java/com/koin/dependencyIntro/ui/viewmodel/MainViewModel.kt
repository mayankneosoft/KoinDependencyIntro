package com.koin.dependencyIntro.ui.viewmodel

import androidx.lifecycle.*
import com.koin.dependencyIntro.data.model.User
import com.koin.dependencyIntro.data.respository.MainRepository
import com.koin.dependencyIntro.utils.NetworkHelper
import com.koin.dependencyIntro.utils.Resource
import kotlinx.coroutines.launch

class MainViewModel (private val mainRepository: MainRepository, private val networkHelper: NetworkHelper) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()

    val users: LiveData<Resource<List<User>>>
        get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.loading(null))

            if (networkHelper.isNetworkConnected()) {

                mainRepository.getUser().let {
                    if (it.isSuccessful) {
                        _users.postValue(Resource.success(it.body()))
                    } else
                        _users.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _users.postValue(Resource.error("No internet connection", null))
        }
    }
}