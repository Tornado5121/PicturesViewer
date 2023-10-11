package com.playrion.picturesviewer.di

import com.playrion.picturesviewer.features.detailedScreen.DetailedViewModel
import com.playrion.picturesviewer.features.listScreen.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featuresModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { (id: String) -> DetailedViewModel(get(), id) }
}