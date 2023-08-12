package com.example.plantapp.Model.Repository

import android.util.Log
import com.example.plantapp.Model.Api.RetroFitClient
import com.example.plantapp.Model.Local.PlantDetailEntity
import com.example.plantapp.Model.Local.PlantsDao
import com.example.plantapp.Model.Mapper.apiPlantDetailEntity
import com.example.plantapp.Model.Mapper.apiPlantEntity

class PlantsRepository(private val plantsDao: PlantsDao) {

    private val networkService = RetroFitClient.retrofitInstance()
    val productsListLiveData = plantsDao.getAllplants()

    suspend fun fetchPlants() {
        try {
            val response = networkService.getPlantsList()
            if (response.isSuccessful) {
                val plantList = response.body()
                plantList?.let { plant ->
                    Log.d("products", plant.toString())
                    plantsDao.insertAllPlants(apiPlantEntity(plant))
                }
            } else {
                Log.d("Error fetching", "${response.code()}-${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("Error", "Error fetching course: ${e.message}", e)
        }
    }

    suspend fun fetchPlantDetail(id: Int): PlantDetailEntity? {
        try {
            val response = networkService.getPlantsDetails(id)
            if (response.isSuccessful) {
                val plantDetail = response.body()
                plantDetail?.let { plant ->
                    val plantData = apiPlantDetailEntity(plant)
                    plantsDao.insertPlantDetail(plantData)
                    return plantData

                }
            } else {
                Log.d("Repo", "${response.code()}-${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("Error", "Error fetching product detail: ${e.message}", e)
        }
        return null
    }

}

