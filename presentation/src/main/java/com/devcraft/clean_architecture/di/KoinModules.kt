package com.devcraft.clean_architecture.di

import com.devcraft.clean_architecture.ui.fragment.adapter.CategoriesAdapter
import com.devcraft.clean_architecture.ui.fragment.adapter.DataAdapterNew
import com.devcraft.clean_architecture.ui.fragment.adapter.DataAdapterOld
import com.devcraft.clean_architecture.ui.fragment.adapter.SubCategoriesAdapter
import com.devcraft.clean_architecture.ui.screen_name.viewmodel.GenericViewModel
import com.devcraft.clean_architecture.ui.vm.MainViewModel
import com.devcraft.domain.interactor.DataInteractor
import com.devcraft.domain.interactor.GenericInteractor
import org.koin.dsl.module

val adaptersModule = module {
    factory { DataAdapterOld() }
    factory { DataAdapterNew() }
    factory { CategoriesAdapter() }
    factory { SubCategoriesAdapter() }
}

val viewModelsModule = module {
    factory { GenericViewModel(get()) }
    factory { MainViewModel(get()) }
}

val interactorsModule = module {
    factory { GenericInteractor() }
    factory { DataInteractor() }
}

