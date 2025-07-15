package com.example.recipeapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun RecipeApp(){
    val navController= rememberNavController()
    val recipeViewModel : MainViewModel= viewModel()
    val viewState by recipeViewModel.categoriesState
    NavHost(navController=navController, startDestination = "recipeScreen") {
        composable("recipeScreen") {
            RecipeScreen(viewState = viewState, navigateToDetails = {
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate("categoryDetailsScreen")
            })
        }
        composable("categoryDetailsScreen") {
            val category= navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>("cat")?: Category("", "", "","")
            CategoryDetailsScreen(category=category)
        }
    }

}