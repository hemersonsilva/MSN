package com.hemerson.msn.di


import com.hemerson.msn.register.RegisterViewModel
import com.hemerson.msn.ui.viewmodels.LoginScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RegisterViewModel() }
    viewModel { LoginScreenViewModel() }
}
