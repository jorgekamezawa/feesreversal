package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.FilaAprovacao

data class FilaAprovacaoView(
    val id: Long,
    val nome: String,
    val nomeRegra: String
)

fun FilaAprovacao.toView() =
    FilaAprovacaoView(
        id = this.id,
        nome = this.nome,
        nomeRegra = this.nomeRegra
    )
