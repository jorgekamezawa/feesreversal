package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.Motivo

data class MotivoForm(
    val nome: String
)

fun MotivoForm.toDomain() =
    Motivo(
        id = 0,
        nome = this.nome
    )

fun MotivoForm.toDomain(id: Long) =
    Motivo(
        id = id,
        nome = this.nome
    )
