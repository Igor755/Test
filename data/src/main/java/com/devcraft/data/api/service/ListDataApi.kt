package com.devcraft.data.api.service

import com.devcraft.data.constant.ApiEndpoints
import com.devcraft.data.entity.network.DataNet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface ListDataApi {
    @GET(ApiEndpoints.ENDPOINT_LIST)
    fun getAllData(): Call<DataNet>
}