package com.hemerson.msn.di


import com.hemerson.msn.ui.viewmodels.RegisterScreenViewModel
import com.hemerson.msn.ui.viewmodels.LoginScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterScreenViewModel() }
    viewModel { LoginScreenViewModel() }
}
