package com.example.findmysquad.DI

import com.example.findmysquad.Repository.AddRepostiory.AddRepository
import com.example.findmysquad.Repository.CadastrarRepository.CadastrarRepository
import com.example.findmysquad.Repository.ConfigRepository.ConfigRepository
import com.example.findmysquad.Repository.LogarRepository.LogarRepository
import com.example.findmysquad.Repository.MyReqRep.MyReqRep
import com.example.findmysquad.Repository.TelaPrincipalRepository.TelaPrincipalRepository
import com.example.findmysquad.ViewModel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { CadastrarRepository() }
    single { AddRepository() }
    single { ConfigRepository() }
    single { LogarRepository() }
    single { TelaPrincipalRepository() }
    single { MyReqRep() }
}


val viewModelModule = module {
    viewModel { CadastrarViewModel(get()) }
    viewModel { ConfigViewModel(get()) }
    viewModel { AddViewModel(get()) }
    viewModel { LogarViewModel(get()) }
    viewModel { TelaPrincipalViewModel(get()) }
    viewModel { MyReqRepViewModel(get()) }
}