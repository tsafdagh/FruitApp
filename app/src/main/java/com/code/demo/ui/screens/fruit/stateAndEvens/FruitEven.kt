package com.code.demo.ui.screens.fruit.stateAndEvens

import com.code.demo.model.FruitModel

sealed class FruitEven {

    data object GenerateFruitList : FruitEven()

    data class OnFruitSelected(val item: FruitModel) : FruitEven()

    data class DeleteFruit(val item: FruitModel) : FruitEven()

}