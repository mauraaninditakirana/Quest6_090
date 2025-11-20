package com.example.quest6.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quest6.model.DataJK.JenisK
import com.example.quest6.view.FormSiswa
import com.example.quest6.view.TampilSiswa
import com.example.quest6.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulirku,
    Tampilan
}

@Composable
fun SiswaApp(
    modifier: Modifier,
    viewModel: SiswaViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),

    ){
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulirku.name,
            modifier = Modifier.padding(isiRuang)) {
            composable(route = Navigasi.Formulirku.name) {
                val konteks = LocalContext.current
                FormSiswa (
                    JenisK.map { id -> konteks.resources.getString(id) },
                    onSubmitButtontnClicked = {
                        viewModel.setSiswa(it)
                        navController.navigate(Navigasi.Tampilan.name)
                    }
                )
            }
            composable(route = Navigasi.Tampilan.name){
                TampilSiswa(
                    statusUISiswa = uiState.value,
                    onBackButtonClicked = {
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