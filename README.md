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

The uiState is stored as a sealed interface 

```
sealed interface FruitUiState {
    object Loading : FruitUiState
    data class FruitUiListSuccess(val fruitList: List<Fruit>) : FruitUiState
    data class Error(val error: String?) : FruitUiState
}
```

and emited with 

```
val fruitUiState: LiveData<FruitUiState> = _fruitUiState
```

to the screen that renders the fruit list

## Navigation

For navigate between composable screens we are using the guide provided by official documentation at [Navigating with compose](https://developer.android.com/jetpack/compose/navigation)

