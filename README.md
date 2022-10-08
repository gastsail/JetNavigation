# JetNavigation
A quick example repo on navigation with compose in a master detail app

## UI Design by [Viktoriia Chyrak](https://dribbble.com/shots/11064072-Fruit-delivery-I/attachments/2660683?mode=media)

![](https://cdn.dribbble.com/users/2556412/screenshots/11064072/media/cf4ebe17094b58079f5c1333904fdf1b.png)

## Architecture
### Unidirectional Data Flow

For drawing UI we use [Unidirectional Data Flow](https://developer.android.com/jetpack/compose/architecture#udf)

![](https://developer.android.com/static/images/jetpack/compose/state-unidirectional-flow.png)

## ViewModel

For emiting these states we use our [FruitViewModel](https://github.com/gastsail/JetNavigation/blob/master/app/src/main/java/com/example/jetnavigation/presentation/feature/fruit/FruitViewModel.kt) which emits the uiState

The uiState is stored as a sealed class 

```
sealed class FruitUiState {
    object Loading : FruitUiState()
    data class FruitListScreenUiState(
        val welcomeUserSectionUiState: WelcomeUserSectionUiState?,
        val infoCardSectionUiState: InfoCardSectionUiState?,
        val weeksBestSellerSectionUiState: WeeksBestSellerSectionUiState?,
        val fruitListSectionUiState: FruitListSectionUiState?
    ) : FruitUiState()

    // TODO SAME AS ABOVE
    // data class FruitDetailScreenUiState()

    data class Error(val exception: Exception) : FruitUiState()
}
```

and emited with 

```
 val uiState = fruitUiViewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            fruitUiViewModelState.value.toUiState()
        )
```

to the screen that renders the fruit list

## Navigation

For navigate between composable screens we are using the guide provided by official documentation at [Navigating with compose](https://developer.android.com/jetpack/compose/navigation)

