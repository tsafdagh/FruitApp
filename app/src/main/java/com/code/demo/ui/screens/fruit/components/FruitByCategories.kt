package com.code.demo.ui.screens.fruit.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.code.demo.model.FruitGroup
import com.code.demo.model.FruitModel

@Composable
fun FruitByCategories(
    selectedTabIndex:Int,
    fruitsCategories: List<FruitGroup>,
    listState: LazyListState = rememberLazyListState(),
    onFruitClicked:(FruitModel) -> Unit
) {
    LazyColumn(
        state = listState,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(fruitsCategories) { _, category ->
            ItemCategoryComponent(category, onFruitClicked = onFruitClicked, isSelected = selectedTabIndex == fruitsCategories.indexOf(category))
        }
    }
}
