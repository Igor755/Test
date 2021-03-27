package com.devcraft.clean_architecture.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcraft.clean_architecture.extension.map
import com.devcraft.clean_architecture.model.Data
import com.devcraft.domain.interactor.DataInteractor
import com.devcraft.domain.model.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val dataInteractor: DataInteractor) : ViewModel() {

    var dataLiveData: LiveData<Result<Data>>
    private var _dataLiveData = MutableLiveData<Result<Data>>()

    init {
        dataLiveData = _dataLiveData
    }

    fun getTastingsFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            dataInteractor.getData({
                _dataLiveData.postValue(Result.Success(it.map()))
            }, {
                _dataLiveData.postValue(Result.Error(it))
            })
        }
    }
}

