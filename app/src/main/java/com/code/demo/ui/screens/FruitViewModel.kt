package com.code.demo.ui.screens

import androidx.lifecycle.ViewModel
import com.code.demo.R
import com.code.demo.model.FruitGroup
import com.code.demo.model.FruitModel
import com.code.demo.ui.screens.fruit.stateAndEvens.FruitEven
import com.code.demo.ui.screens.fruit.stateAndEvens.FruitUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar
import java.util.UUID
import kotlin.random.Random

class FruitViewModel : ViewModel() {


    private val _fruitScreenState = MutableStateFlow(FruitUiState())
    val fruitScreenState: StateFlow<FruitUiState> = _fruitScreenState


/*    private val _fruitCategories = MutableLiveData<List<FruitGroup>>()
    val fruitCategories: LiveData<List<FruitGroup>> = _fruitCategories*/


    fun onEven(event: FruitEven){
        when(event){
            is FruitEven.GenerateFruitList ->{
                createData()
            }
            is FruitEven.DeleteFruit ->{
                deleteFruit(event.item)
            }

            is FruitEven.OnFruitSelected ->{
                _fruitScreenState.value = _fruitScreenState.value.copy(selectedFruit = event.item)
            }
        }
    }


/**
 * Generates a list of fruit categories, each containing a list of fruits.
 * This function creates 30 categories, each with a random number of fruits between 2 and 6.
 * Each fruit is either a "Apple" or "Mango", with corresponding details and expiration dates.
 * The expiration date for each category starts from July 1, 2024, and increases by one day for each subsequent category.
 * The fruits within a category have their expiration dates set to the category's expiration date.
 */
fun createData() {
    val categories = List(30) { index ->
        // Generate a list of fruits for the current category
        val items = List(1 + Random.nextInt(6)) { itemIndex ->
            FruitModel(
                id = UUID.randomUUID().toString(), // Unique identifier for the fruit
                name = if ((index + itemIndex) % 2 == 0) "Apple" else "Mango", // Name of the fruit
                imageResource = if ((index + itemIndex) % 2 == 0) R.drawable.orange_fruit else R.drawable.mangos, // Image resource for the fruit
                expirationDate = Calendar.getInstance().apply { set(2024, Calendar.JULY, 1 + index) }.time, // Expiration date of the fruit
                detail = if ((index + itemIndex) % 2 == 0) "Fruit mango details generated test form the demo" else "Mango details, details generated test form the demo" // Detail description of the fruit
            )
        }
        // Create a fruit group for the current category
        FruitGroup(
            groupDate = Calendar.getInstance().apply { set(2024, Calendar.JULY, 1 + index) }.time, // Expiration date for the category
            fruitList = items as MutableList // List of fruits in the category
        )
    }

    _fruitScreenState.value = _fruitScreenState.value.copy(
        categories=categories as MutableList
    )
}

   fun deleteFruit(fruitToDelete: FruitModel) {

       val categories = _fruitScreenState.value.categories.map { category ->
            val newItems = category.fruitList.filter { it.id != fruitToDelete.id }.toMutableList()
            FruitGroup(category.groupDate, newItems)
        }

        _fruitScreenState.value = _fruitScreenState.value.copy(categories=categories as MutableList,
            selectedFruit = null)
    }
}
