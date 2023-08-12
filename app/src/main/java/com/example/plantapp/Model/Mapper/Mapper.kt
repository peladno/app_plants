package com.example.plantapp.Model.Mapper

import com.example.plantapp.Model.Api.Model.PlantDetail
import com.example.plantapp.Model.Api.Model.PlantsList
import com.example.plantapp.Model.Local.PlantDetailEntity
import com.example.plantapp.Model.Local.PlantListEntity

fun apiPlantEntity(plantList: List<PlantsList>): List<PlantListEntity> {
    return plantList.map { plant ->
        PlantListEntity(
            id = plant.id,
            name = plant.nombre,
            description = plant.descripcion,
            type = plant.tipo,
            image = plant.imagen
        )
    }
}

fun apiPlantDetailEntity(plant: PlantDetail): PlantDetailEntity {
    return PlantDetailEntity(
        id = plant.id,
        name = plant.nombre,
        description = plant.descripcion,
        type = plant.tipo,
        image = plant.imagen
    )
}
