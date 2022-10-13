package com.example.jetnavigation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetnavigation.data.*
import com.example.jetnavigation.ui.screens.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FruitViewModel @Inject constructor(private val fruitRepository: LocalFruitRepository) :
    ViewModel() {

    private val fruitUiViewModelState = MutableStateFlow(FruitUiViewModelState())

    val uiState = fruitUiViewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            fruitUiViewModelState.value.toUiState()
        )

    init {
        getAllFruits()
    }

    private fun getAllFruits() {
        viewModelScope.launch {
            fruitUiViewModelState.update { fruitUiViewModelState ->
                fruitUiViewModelState.copy(
                    fruitList = emptyList()
                )
            }
            runCatching {
                fruitRepository.getAllFruits()
            }.onSuccess { fruitList ->
                val fruitScreenData = FruitScreenData(
                    user = User(title = "Hello", name = "Elon !"),
                    infoCard = InfoCard("We picked up a new portion of fresh fruits for you!"),
                    weeksBestSeller = WeeksBestSeller("Week's bestsellers"),
                    fruitList = fruitList
                )
                fruitUiViewModelState.update { fruitUiViewModelState ->
                    fruitUiViewModelState.copy(
                        fruitList = fruitScreenData.fruitList,
                        user = fruitScreenData.user,
                        infoCard = fruitScreenData.infoCard,
                        weeksBestSeller = fruitScreenData.weeksBestSeller
                    )
                }
            }.onFailure { throwable ->
                fruitUiViewModelState.update { fruitUiViewModelState ->
                    fruitUiViewModelState.copy(
                        error = java.lang.Exception(throwable.message)
                    )
                }
            }
        }
    }

    //TODO CHALLENGE, AGREGAR ESTE METODO PARA CARGAR FruitDetailScreen NAVEGANDO DESDE FruitListScreen
    // Pro-tip : https://youtu.be/0z_dwBGQQWQ?t=876
    fun findFruitById(fruitId: Long?) {
        viewModelScope.launch {
            fruitUiViewModelState.update { fruitUiViewModelState ->
                fruitUiViewModelState.copy(
                    fruitList = emptyList()
                )
            }
            runCatching {
                fruitId?.let {
                    fruitRepository.getFruitById(it)
                }
            }.onSuccess { fruit ->
                // TODO
            }.onFailure { throwable ->
                fruitUiViewModelState.update { fruitUiViewModelState ->
                    fruitUiViewModelState.copy(
                        error = java.lang.Exception(throwable.message)
                    )
                }
            }
        }
    }
}

sealed class FruitUiState {
    object Loading : FruitUiState()
    data class FruitListScreenUiState(
        val welcomeUserSectionUiState: WelcomeUserSectionUiState?,
        val infoCardSectionUiState: InfoCardSectionUiState?,
        val weeksBestSellerSectionUiState: WeeksBestSellerSectionUiState?,
        val fruitListSectionUiState: FruitListSectionUiState?
    ) : FruitUiState()
    data class Error(val exception: Exception) : FruitUiState()
}

data class FruitUiViewModelState(
    val fruitList: List<Fruit> = emptyList(),
    val user: User? = null,
    val infoCard: InfoCard? = null,
    val weeksBestSeller: WeeksBestSeller? = null,
    val error: Exception? = null
) {

    fun toUiState(): FruitUiState {
        if (fruitList.isEmpty()) return FruitUiState.Loading
        if (error != null) return FruitUiState.Error(error)

        val welcomeUserSectionUiState = WelcomeUserSectionUiState(user = user)
        val infoCardSectionUiState = InfoCardSectionUiState(infoCard = infoCard)
        val weeksBestSellerSectionUiState = WeeksBestSellerSectionUiState(weeksBestSeller = weeksBestSeller)
        val fruitListSectionUiState = FruitListSectionUiState(fruitList = fruitList)

        return FruitUiState.FruitListScreenUiState(
            welcomeUserSectionUiState = welcomeUserSectionUiState,
            infoCardSectionUiState = infoCardSectionUiState,
            weeksBestSellerSectionUiState = weeksBestSellerSectionUiState,
            fruitListSectionUiState = fruitListSectionUiState
        )
    }
}