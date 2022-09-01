package com.example.jetnavigation.presentation.feature.showfruits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnavigation.data.Fruit
import com.example.jetnavigation.data.LocalFruitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(private val fruitRepository: LocalFruitRepository) :
    ViewModel() {

    private val _fruitUiState = MutableLiveData(FruitUiState())
    val fruitUiState: LiveData<FruitUiState> = _fruitUiState

    init {
        getAllFruits()
    }

    fun getAllFruits() {
        viewModelScope.launch {
            runCatching {
                updateFruitUiState(isLoading = true)
                fruitRepository.getAllFruits()
            }.onSuccess { fruits ->
                updateFruitUiState(isLoading = false, fruits = fruits)
            }.onFailure {
                updateFruitUiState(isLoading = false, error = it.message)
            }
        }
    }

    fun findFruitById(id: Long) {
        viewModelScope.launch {
            runCatching {
                updateFruitUiState(isLoading = true)
                fruitRepository.getFruitById(id)
            }.onSuccess { fruit ->
                fruit?.let {
                    updateFruitUiState(isLoading = false, fruits = listOf(it))
                } ?: run {
                    updateFruitUiState(isLoading = false, error = "Fruit with id: $id not found")
                }

            }.onFailure {
                updateFruitUiState(isLoading = false, error = it.message)
            }
        }
    }

    private fun updateFruitUiState(
        isLoading: Boolean = false,
        fruits: List<Fruit> = listOf(),
        error: String? = null,
    ) {
        _fruitUiState.value =
            fruitUiState.value?.copy(isLoading = isLoading, fruits = fruits, error = error)
    }
}

data class FruitUiState(
    val isLoading: Boolean = false,
    val fruits: List<Fruit> = emptyList(),
    val error: String? = null,
)