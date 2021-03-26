package com.devcraft.data.api.data_source

import android.accounts.NetworkErrorException
import com.devcraft.data.api.service.ListDataApi
import com.devcraft.data.entity.network.DataNet
import com.devcraft.data.extensions.map
import com.devcraft.domain.model.AllDataModel
import com.devcraft.domain.model.DataModel
import com.devcraft.domain.throwException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataNetSource(private val listDataApi: ListDataApi) {
   /* fun getAllData(onSuccess: (datanet: DataNet) -> Unit, onError: (Exception) -> Unit) {
        listDataApi.getAllData()
            .enqueue(object : Callback<DataNet> {
                override fun onFailure(call: Call<DataNet>, t: Throwable) {
                    onError(NetworkErrorException())
                }
                override fun onResponse(call: Call<DataNet>, response: Response<DataNet>) {
                    if (response.isSuccessful && response.body() != null) {
                        onSuccess(response.body()!!)
                    } else {
                        onError(response.code().throwException(response.message()))
                    }
                }
            })
    }*/
    fun getAllData(onComplete: (DataModel) -> Unit, onError: (Exception) -> Unit?) {
       listDataApi.getAllData().enqueue(object : Callback<DataNet> {
            override fun onFailure(call: Call<DataNet>, t: Throwable) {
                onError(com.devcraft.domain.exception.NetworkErrorException())
            }
            override fun onResponse(
                call: Call<DataNet>,
                response: Response<DataNet>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onComplete(it.map())
                    }
                } else {
                    onError(response.code().throwException(response.message()))
                }
            }
        })
    }
}