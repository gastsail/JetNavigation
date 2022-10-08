package com.example.jetnavigation.presentation

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

    private val _fruitUiState = MutableLiveData<FruitUiState>()
    val fruitUiState: LiveData<FruitUiState> = _fruitUiState

    init {
        getAllFruits()
    }

    private fun getAllFruits() {
        viewModelScope.launch {
            runCatching {
                _fruitUiState.value = FruitUiState.Loading
                fruitRepository.getAllFruits()
            }.onSuccess {
                _fruitUiState.value = FruitUiState.FruitUiListSuccess(fruitList = it)
            }.onFailure {
                _fruitUiState.value = FruitUiState.Error(error = it.message)
            }
        }
    }

    fun findFruitById(fruitId: Long?) {
        viewModelScope.launch {
            runCatching {
                fruitId?.let {
                    _fruitUiState.value = FruitUiState.Loading
                    fruitRepository.getFruitById(it)
                }
            }.onSuccess { fruit ->
               // TODO
            }.onFailure {
                FruitUiState.Error(error = it.message)
            }
        }
    }
}

sealed interface FruitUiState {
    object Loading : FruitUiState
    data class FruitUiListSuccess(val fruitList: List<Fruit>) : FruitUiState
    data class Error(val error: String?) : FruitUiState
}