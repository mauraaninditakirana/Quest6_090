package com.example.quest6.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quest6.view.FormIsian
import com.example.quest6.view.TampilData

enum class Navigasi {
    Formulirku,
    Tampilan
}

@Composable
fun DataApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier
){
    Scaffold { isiRuang->
        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,

            modifier = Modifier.padding(isiRuang)) {
            composable(route = Navigasi.Formulirku.name) {
                FormIsian (
                    //pilihan JK = JenisK.map { id -> konteks.resources.getString(id) },
                    OnSubmitBtnClick = {
                        navController.navigate(Navigasi.Tampilan.name)
                    }
                )
            }
            composable(route = Navigasi.Tampilan.name){
                TampilData(
                    onBackBtnClick = {
                        cancelAndBackToFormulirku(navController)
                    }
                )
            }
        }
    }
}
private fun cancelAndBackToFormulirku(
    navController: NavHostController
) {
    navController.popBackStack(Navigasi.Formulirku.name,
        inclusive = false)
}