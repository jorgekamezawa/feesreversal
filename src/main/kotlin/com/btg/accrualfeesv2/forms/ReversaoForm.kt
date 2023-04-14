package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.*
import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import java.math.BigDecimal

data class ReversaoForm(
    val nome: String,
    val numeroConta: String,
    val tipoConta: String,
    val numeroDocumento: String,
    val valorTransacao: BigDecimal,
    val valorReversao: BigDecimal,
    val justificativa: String,
    val segmentoCliente: String,
    val tipoOperacaoId: Long,
    val motivoId: Long,
    val tipoReversaoId: Long
)

fun ReversaoForm.toDomain(
    txLoginCriador: String,
    tipoOperacao: TipoOperacao,
    motivo: Motivo,
    tipoReversao: TipoReversao,
    filaAprovacao: FilaAprovacao
) =
    Reversao(
        id = 0,
        nome = this.nome,
        numeroConta = this.numeroConta,
        tipoConta = this.tipoConta,
        numeroDocumento = this.numeroDocumento,
        valorTransacao = this.valorTransacao,
        valorReversao = this.valorReversao,
        justificativa = this.justificativa,
        statusAprovacao = StatusAprovacaoEnum.PENDENTE_APROVACAO,
        segmentoCliente = this.segmentoCliente,
        tipoOperacao = tipoOperacao,
        motivo = motivo,
        tipoReversao = tipoReversao,
        filaAprovacao = filaAprovacao,
        txLoginCriador = txLoginCriador
    )

fun ReversaoForm.toDomain(
    txLoginCriador: String,
    tipoOperacao: TipoOperacao,
    motivo: Motivo,
    tipoReversao: TipoReversao,
    filaAprovacao: FilaAprovacao,
    id: Long
) =
    Reversao(
        id = id,
        nome = this.nome,
        numeroConta = this.numeroConta,
        tipoConta = this.tipoConta,
        numeroDocumento = this.numeroDocumento,
        valorTransacao = this.valorTransacao,
        valorReversao = this.valorReversao,
        justificativa = this.justificativa,
        statusAprovacao = StatusAprovacaoEnum.PENDENTE_APROVACAO,
        segmentoCliente = this.segmentoCliente,
        tipoOperacao = tipoOperacao,
        motivo = motivo,
        tipoReversao = tipoReversao,
        filaAprovacao = filaAprovacao,
        txLoginCriador = txLoginCriador
    )
