package com.code.demo.ui.screens.fruit.stateAndEvens

import com.code.demo.model.FruitGroup
import com.code.demo.model.FruitModel

data class FruitUiState (

    val categories: MutableList<FruitGroup> = mutableListOf(),
    val selectedFruit:FruitModel? = null
)