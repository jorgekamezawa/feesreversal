package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.Motivo

data class MotivoView(
    val id: Long,
    val nome: String
)

fun Motivo.toView() =
    MotivoView(
        id = this.id,
        nome = this.nome
    )
