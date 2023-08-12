package com.example.plantapp.Model.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitClient {

    companion object {

        private const val BASE_URL = "https://my-json-server.typicode.com/mauricioponce/TDApi/"


        fun retrofitInstance(): FetchMethods {

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(FetchMethods::class.java)
        }


    }
}