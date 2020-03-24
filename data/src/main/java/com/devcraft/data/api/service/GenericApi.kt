package com.devcraft.data.api.service

import com.devcraft.data.constant.ApiEndpoints
import com.devcraft.data.entity.network.GenericNet
import retrofit2.Call
import retrofit2.http.GET

interface GenericApi {
    @GET(ApiEndpoints.ENDPOINT_NAME)
    fun getCategories(): Call<List<GenericNet>>
}