package com.example.plantapp.Model.Local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plant_detail_table")
data class PlantDetailEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    val type : String,
    val description: String
)