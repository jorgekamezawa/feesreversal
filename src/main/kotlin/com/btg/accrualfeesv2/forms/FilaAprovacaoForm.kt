package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.FilaAprovacao

data class FilaAprovacaoForm(
    val nome: String,
    val nomeRegra: String
)

fun FilaAprovacaoForm.toDomain() =
    FilaAprovacao(
        id = 0,
        nome = this.nome,
        nomeRegra = this.nomeRegra
    )

fun FilaAprovacaoForm.toDomain(id: Long) =
    FilaAprovacao(
        id = id,
        nome = this.nome,
        nomeRegra = this.nomeRegra
    )
