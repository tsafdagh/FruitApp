package com.code.demo.ui.screens.commonComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.code.demo.model.FruitGroup
import com.code.demo.ui.theme.greenColor
import com.code.demo.utils.getDayFromDate
import com.code.demo.utils.getDayOfWeek

@Composable
fun MyAppTabBar(
    categories: List<FruitGroup>,
    selectedTabIndex: Int,
    onTabClicked: (index: Int, category: FruitGroup) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp,
        divider = { },
        indicator = {}
    ) {
        categories.forEachIndexed { index, categoryTemp ->

            val backgroundColor = if (index == selectedTabIndex) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            } else {
                Color.Transparent
            }

            val textColor = if (index == selectedTabIndex) {
                MaterialTheme.colorScheme.primary
            } else {
                Color.LightGray
            }
            Tab(
                selected = index == selectedTabIndex,
                onClick = { onTabClicked(index, categoryTemp) },
                selectedContentColor = Color.Transparent,
                text = {
                    Box(
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(8.dp),
                                color = backgroundColor
                            )
                            .padding(horizontal = 8.dp, vertical = 6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .wrapContentWidth()
                                .align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                categoryTemp.groupDate.getDayFromDate().toString(),
                                color = textColor, fontWeight = if(selectedTabIndex==index) FontWeight.Bold else FontWeight.Normal
                            )
                            Text(getDayOfWeek(categoryTemp.groupDate),
                                color = textColor, fontWeight = if(selectedTabIndex==index) FontWeight.Bold else FontWeight.Normal
                            )
                            Row(modifier = Modifier.wrapContentWidth()) {
                                categoryTemp.fruits.forEach {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = textColor,
                                                shape = CircleShape
                                            )
                                            .size(3.dp)
                                    )
                                    Spacer(modifier = Modifier.width(1.dp))
                                }
                            }
                        }
                    }
                })
        }
    }
}
