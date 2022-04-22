package com.example.findmysquad.Model

data class ModelRequisicoes (
    var game : ArrayList<Int> = arrayListOf(),
    var horario : String = "",
    var user : String = "",
    var plataforma : ArrayList<Int> = arrayListOf()
        )