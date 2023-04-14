package com.btg.accrualfeesv2.domains

import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import java.math.BigDecimal

data class Reversao(
    val id: Long = 0,
    val nome: String,
    val numeroConta: String,
    val tipoConta: String,
    val numeroDocumento: String,
    val valorTransacao: BigDecimal,
    val valorReversao: BigDecimal,
    val justificativa: String,
    var statusAprovacao: StatusAprovacaoEnum,
    val segmentoCliente: String,
    val txLoginCriador: String,
    val tipoOperacao: TipoOperacao,
    val motivo: Motivo,
    val tipoReversao: TipoReversao,
    val filaAprovacao: FilaAprovacao
)
