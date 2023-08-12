package com.example.plantapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.plantapp.Model.Local.PlantDetailEntity
import com.example.plantapp.Model.Local.PlantListEntity
import com.example.plantapp.Model.Local.PlantsDatabase
import com.example.plantapp.Model.Repository.PlantsRepository
import kotlinx.coroutines.launch

class PlantViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: PlantsRepository
    private val plantDetailLiveData = MutableLiveData<PlantDetailEntity?>()
    private val plantListLiveData: LiveData<List<PlantListEntity>>
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        val db = PlantsDatabase.getDatabase(application)
        val productDao = db.getProductsDao()

        repository = PlantsRepository(productDao)
        _isLoading.value = true

        viewModelScope.launch {
            try {
                repository.fetchPlants()
            } finally {
                _isLoading.value = false
            }
        }

        plantListLiveData = repository.productsListLiveData
    }

    fun getPlantList(): LiveData<List<PlantListEntity>> = plantListLiveData
    fun getPlantDetail(): MutableLiveData<PlantDetailEntity?> = plantDetailLiveData

    fun fetchPlantDetail(id: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val productDetail = repository.fetchPlantDetail(id)
                productDetail?.let {
                    if (it != plantDetailLiveData.value) {
                        plantDetailLiveData.value = it
                    }
                }
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearData() {
        plantDetailLiveData.value = null
    }

}