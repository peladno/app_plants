package com.example.plantapp.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlantsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPlants(plantList: List<PlantListEntity>)

    @Query("Select * FROM plant_list_table ORDER BY id ASC")
    fun getAllplants(): LiveData<List<PlantListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlantDetail(course: PlantDetailEntity)

    @Query("SELECT * FROM plant_detail_table WHERE id=:id")
    fun getPlantDetailById(id: String): LiveData<PlantDetailEntity>

}