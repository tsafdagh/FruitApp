package com.code.demo.ui.screens.fruit.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.code.demo.R
import com.code.demo.model.FruitGroup
import com.code.demo.model.FruitModel
import com.code.demo.utils.convertDateStringFormat

@Composable
fun ItemCategoryComponent(
    category: FruitGroup,
    isSelected: Boolean = false,
    onFruitClicked: (FruitModel) -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            text = category.groupDate.convertDateStringFormat(),
            fontSize = 18.sp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Unspecified
        )

        Spacer(Modifier.height(10.dp))

        LazyRow {
            items(category.fruits.size) { fruit ->
                FruitItemComponent(item = category.fruits[fruit], onFruitClicked = onFruitClicked)
                Spacer(modifier = Modifier.width(6.dp))
            }
        }

        if (category.fruits.isEmpty())
            Text(text = stringResource(R.string.pas_de_produit_perimer))

    }
}
