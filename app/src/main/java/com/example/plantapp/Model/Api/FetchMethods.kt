package com.example.plantapp.Model.Api

import com.example.plantapp.Model.Api.Model.PlantDetail
import com.example.plantapp.Model.Api.Model.PlantsList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FetchMethods {

    @GET("plantas")
    suspend fun getPlantsList(): Response<List<PlantsList>>

    @GET("plantas/{id}")
    suspend fun getPlantsDetails(@Path("id") id: Int): Response<PlantDetail>


}