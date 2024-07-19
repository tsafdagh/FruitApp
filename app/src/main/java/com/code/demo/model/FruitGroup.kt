package com.code.demo.model

import java.util.Date

class FruitGroup(val groupDate: Date, val fruitList: MutableList<FruitModel>) {
    val fruits: List<FruitModel> = fruitList.toList()
}