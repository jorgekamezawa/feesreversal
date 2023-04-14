package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.TipoReversao

data class TipoReversaoFrom(
    val nome: String
)

fun TipoReversaoFrom.toDomain() =
    TipoReversao(
        id = 0,
        nome = this.nome
    )

fun TipoReversaoFrom.toDomain(id: Long) =
    TipoReversao(
        id = id,
        nome = this.nome
    )
