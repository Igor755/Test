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

    var setLiveData: LiveData<Result<Data>>
    private var _setLiveData = MutableLiveData<Result<Data>>()

    init {
        setLiveData = _setLiveData
    }

    fun getTastingsFromServer() {
        viewModelScope.launch(Dispatchers.IO) {
            dataInteractor.getData({
                _setLiveData.postValue(Result.Success(it.map()))
            }, {
                _setLiveData.postValue(Result.Error(it))
            })
        }
    }
}

