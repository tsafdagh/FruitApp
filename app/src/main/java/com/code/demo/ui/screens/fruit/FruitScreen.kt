package com.code.demo.ui.screens.fruit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ahmadhamwi.tabsync_compose.lazyListTabSync
import com.code.demo.model.FruitGroup
import com.code.demo.model.FruitModel
import com.code.demo.ui.screens.FruitViewModel
import com.code.demo.ui.screens.fruit.components.FruitByCategories
import com.code.demo.ui.screens.commonComponents.MyAppTabBar
import com.code.demo.ui.screens.fruit.stateAndEvens.FruitEven
import com.code.demo.ui.screens.fruitDetails.FruitDetailBottomSheet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FruitScreen(viewModel: FruitViewModel) {

    LaunchedEffect(key1 = "FruitScreenKey") {
        viewModel.onEven(FruitEven.GenerateFruitList)
    }

    val fruitScreenState = viewModel.fruitScreenState.collectAsState()

    val fruitsCategories = fruitScreenState.value.categories
    val fruitsModalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    if (fruitsCategories.isNotEmpty()) {

        Scaffold(modifier = Modifier.fillMaxSize()) { padding ->

            FruitScreenContain(
                modifier = Modifier.padding(padding),
                padding = padding,
                coroutineScope = coroutineScope,
                fruitsCategories = fruitsCategories,
                modalBottomSheetState = fruitsModalBottomSheetState,
                onFruitSelected = { fruitSelection ->
                    viewModel.onEven(FruitEven.OnFruitSelected(fruitSelection))
                }
            )
            if (fruitsModalBottomSheetState.isVisible) {
                ModalBottomSheet(
                    onDismissRequest = {
                        coroutineScope.launch {
                            fruitsModalBottomSheetState.hide()
                        }
                    },
                    sheetState = fruitsModalBottomSheetState,
                    dragHandle = { BottomSheetDefaults.DragHandle() },
                ) {
                    fruitScreenState.value.selectedFruit?.let { fruitItem ->
                        FruitDetailBottomSheet(fruitModel = fruitItem, onCloseClicked = {
                            coroutineScope.launch {
                                fruitsModalBottomSheetState.hide()
                            }
                            viewModel.onEven(FruitEven.DeleteFruit(fruitItem))

                        }, onArchiveClick = {
                            viewModel.onEven(FruitEven.DeleteFruit(fruitItem))
                            coroutineScope.launch {
                                fruitsModalBottomSheetState.hide()
                            }
                        },
                            onDeleteClicked = {
                                viewModel.onEven(FruitEven.DeleteFruit(fruitItem))
                                coroutineScope.launch {
                                    fruitsModalBottomSheetState.hide()
                                }
                            })

                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FruitScreenContain(
    modifier: Modifier = Modifier,
    padding: PaddingValues,
    coroutineScope: CoroutineScope,
    fruitsCategories: List<FruitGroup>,
    onFruitSelected: (FruitModel) -> Unit,
    modalBottomSheetState: SheetState
) {

    val (selectedTabIndex, setSelectedTabIndex, listState) = lazyListTabSync(fruitsCategories.indices.toList())


    Column(modifier = modifier.padding(padding)) {
        MyAppTabBar(
            categories = fruitsCategories,
            selectedTabIndex = (selectedTabIndex - 3),
            onTabClicked = { index, _ ->
                setSelectedTabIndex(index)
            }
        )

        FruitByCategories(
            selectedTabIndex= selectedTabIndex-3,
            fruitsCategories=fruitsCategories,
            listState=listState,
            onFruitClicked = { fruitItem ->
                coroutineScope.launch {
                    modalBottomSheetState.show()
                    onFruitSelected(fruitItem)
                }
            })

    }
}