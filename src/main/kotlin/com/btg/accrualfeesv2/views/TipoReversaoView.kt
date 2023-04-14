package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.TipoReversao

data class TipoReversaoView(
    val id: Long,
    val nome: String
)

fun TipoReversao.toView() =
    TipoReversaoView(
        id = this.id,
        nome = this.nome
    )
