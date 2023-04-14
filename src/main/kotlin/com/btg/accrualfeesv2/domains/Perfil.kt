package com.btg.accrualfeesv2.domains

data class Perfil(
    val id: Long = 0,
    val nome: String,
    val motivoList: List<Motivo>
)
