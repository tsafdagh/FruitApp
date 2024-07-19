package com.code.demo

import com.code.demo.ui.screens.FruitViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FruitViewModelTest {

    private lateinit var viewModel: FruitViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = FruitViewModel()
    }

    @Test
    fun `createData populates categories with correct structure`(): Unit = runTest {
        viewModel.createData()
        val state = viewModel.fruitScreenState.value

        assertTrue("Categories should not be empty", state.categories.isNotEmpty())
        assertTrue("Each category should have 1 to 5 fruits", state.categories.all { it.fruitList.size in 1..5 })
    }

    @Test
    fun `deleteFruit removes the specified fruit from categories`() = runTest {
        viewModel.createData() // Populate data first
        val initialFruit = viewModel.fruitScreenState.value.categories.first().fruitList.first()

        viewModel.deleteFruit(initialFruit)
        val stateAfterDeletion = viewModel.fruitScreenState.value

        assertFalse("Deleted fruit should not exist in any category",
            stateAfterDeletion.categories.any { category ->
                category.fruitList.any { it.id == initialFruit.id }
            })
    }
}