package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.TipoOperacao

data class TipoOperacaoView(
    val id: Long,
    val nome: String,
    val typeCode: String,
    val reversionTypeCode: String,
    val tipoReversao: TipoReversaoView
)

fun TipoOperacao.toView() =
    TipoOperacaoView(
        id = this.id,
        nome = this.nome,
        typeCode = this.typeCode,
        reversionTypeCode = this.reversionTypeCode,
        tipoReversao = tipoReversao.toView()
    )
