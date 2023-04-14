package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.Reversao
import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import java.math.BigDecimal

data class ReversaoView(
    val id: Long,
    val nome: String,
    val numeroConta: String,
    val tipoConta: String,
    val numeroDocumento: String,
    val valorTransacao: BigDecimal,
    val valorReversao: BigDecimal,
    val justificativa: String,
    val statusAprovacao: StatusAprovacaoEnum,
    val segmentoCliente: String,
    val txLoginCriador: String,
    val tipoOperacao: TipoOperacaoView,
    val motivo: MotivoView,
    val tipoReversao: TipoReversaoView,
    val filaAprovacao: FilaAprovacaoView,
    val anexoUrl: String? = null
)

fun Reversao.toView(anexoUrl: String? = null) =
    ReversaoView(
        id = this.id,
        nome = this.nome,
        numeroConta = this.numeroConta,
        tipoConta = this.tipoConta,
        numeroDocumento = this.numeroDocumento,
        valorTransacao = this.valorTransacao,
        valorReversao = this.valorReversao,
        justificativa = this.justificativa,
        statusAprovacao = this.statusAprovacao,
        segmentoCliente = this.segmentoCliente,
        tipoOperacao = tipoOperacao.toView(),
        motivo = motivo.toView(),
        tipoReversao = tipoReversao.toView(),
        filaAprovacao = filaAprovacao.toView(),
        txLoginCriador = txLoginCriador,
        anexoUrl = anexoUrl
    )
