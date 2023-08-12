package com.example.plantapp.Model.Local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [PlantListEntity::class, PlantDetailEntity::class],
    version = 1,
    exportSchema = false
)
abstract class PlantsDatabase : RoomDatabase() {

    abstract fun getProductsDao(): PlantsDao

    companion object {
        @Volatile
        private var
                INSTANCE: PlantsDatabase? = null

        fun getDatabase(context: Context): PlantsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantsDatabase::class.java, "PlantsList"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}