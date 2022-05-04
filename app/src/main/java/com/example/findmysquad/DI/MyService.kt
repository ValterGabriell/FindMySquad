package com.example.findmysquad.DI

import com.example.findmysquad.Repository.AddRequisitionRepostiory.AddRequisitonRepository
import com.example.findmysquad.Repository.CadastrarRepository.CadastrarRepository
import com.example.findmysquad.Repository.ConfigNewUserRepository.ConfigNewUserRepository
import com.example.findmysquad.Repository.EditProfileRepository.EditProfileRepository
import com.example.findmysquad.Repository.LogarRepository.LogarRepository
import com.example.findmysquad.Repository.MyReqRep.MyReqRep
import com.example.findmysquad.Repository.TelaPrincipalRepository.TelaPrincipalRepository
import com.example.findmysquad.ViewModel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single { CadastrarRepository() }
    single { AddRequisitonRepository() }
    single { ConfigNewUserRepository() }
    single { LogarRepository() }
    single { TelaPrincipalRepository() }
    single { MyReqRep() }
    single { EditProfileRepository() }
}


val viewModelModule = module {
    viewModel { CadastrarViewModel(get()) }
    viewModel { ConfigViewModel(get()) }
    viewModel { AddRequisitonViewModel(get()) }
    viewModel { LogarViewModel(get()) }
    viewModel { TelaPrincipalViewModel(get()) }
    viewModel { MyReqRepViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }
}