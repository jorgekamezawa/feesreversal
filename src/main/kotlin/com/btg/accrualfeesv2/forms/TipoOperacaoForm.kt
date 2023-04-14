package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.TipoOperacao
import com.btg.accrualfeesv2.domains.TipoReversao

data class TipoOperacaoForm(
    val nome: String,
    val typeCode: String,
    val reversionTypeCode: String,
    val tipoReversaoId: Long
)

fun TipoOperacaoForm.toDomain(tipoReversao: TipoReversao) =
    TipoOperacao(
        id = 0,
        nome = this.nome,
        typeCode = this.typeCode,
        reversionTypeCode = this.reversionTypeCode,
        tipoReversao = tipoReversao
    )

fun TipoOperacaoForm.toDomain(tipoReversao: TipoReversao, id: Long) =
    TipoOperacao(
        id = id,
        nome = this.nome,
        typeCode = this.typeCode,
        reversionTypeCode = this.reversionTypeCode,
        tipoReversao = tipoReversao
    )
