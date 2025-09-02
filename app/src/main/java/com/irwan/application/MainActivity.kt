package com.irwan.application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.irwan.application.presentation.UsersScreen
import com.irwan.application.presentation.UsersViewModel
import com.irwan.application.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    private val vm by viewModels<UsersViewModel> { UsersViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MyApplicationTheme {
                    UsersScreen(viewModel = vm)
                }
            }
        }
    }
}