package com.btg.accrualfeesv2.domains

data class TipoOperacao(
    val id: Long,
    val nome: String,
    val typeCode: String,
    val reversionTypeCode: String,
    val tipoReversao: TipoReversao
)
