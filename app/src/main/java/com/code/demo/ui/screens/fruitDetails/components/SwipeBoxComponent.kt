package com.code.demo.ui.screens.fruitDetails.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeBoxComponent(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onEatFruit: () -> Unit,
    onSwipeEndToStart: () -> Unit,
    onSwipeStartToEnd: () -> Unit,
    onSwipeSettled: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeToDismissBoxState()

    when (swipeState.dismissDirection) {
        SwipeToDismissBoxValue.EndToStart -> {

            onSwipeEndToStart()
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            onSwipeStartToEnd()
        }

        SwipeToDismissBoxValue.Settled -> {
            onSwipeSettled()
        }
    }

    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = swipeState,
        backgroundContent = {
            Box(modifier = Modifier.background(color = Color.White)) {
            }
        }
    ) {
        content()
    }

    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            onDelete()
            onSwipeEndToStart()
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            onEatFruit()
            onSwipeStartToEnd()
            LaunchedEffect(swipeState) {
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.Settled -> {
            onSwipeSettled()
        }
    }
}