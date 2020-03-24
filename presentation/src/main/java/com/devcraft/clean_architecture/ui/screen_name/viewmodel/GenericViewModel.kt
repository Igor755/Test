package com.devcraft.clean_architecture.ui.screen_name.viewmodel

import androidx.lifecycle.ViewModel
import com.devcraft.domain.interactor.GenericInteractor

class GenericViewModel(
    private val interactor: GenericInteractor
) : ViewModel()