package com.devcraft.clean_architecture.di

import com.devcraft.clean_architecture.ui.screen_name.viewmodel.GenericViewModel
import com.devcraft.domain.interactor.GenericInteractor
import org.koin.dsl.module

val adaptersModule = module {}

val viewModelsModule = module {
    factory { GenericViewModel(get()) }
}

val interactorsModule = module {
    factory { GenericInteractor() }
}

