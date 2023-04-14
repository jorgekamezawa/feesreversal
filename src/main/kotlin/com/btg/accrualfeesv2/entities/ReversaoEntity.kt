package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.Reversao
import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tb_reversao")
class ReversaoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "nome")
    val nome: String,
    @Column(name = "numeroConta")
    val numeroConta: String,
    @Column(name = "tipoConta")
    val tipoConta: String,
    @Column(name = "numeroDocumento")
    val numeroDocumento: String,
    @Column(name = "valorTransacao")
    val valorTransacao: BigDecimal,
    @Column(name = "valorReversao")
    val valorReversao: BigDecimal,
    @Column(name = "justificativa")
    val justificativa: String,
    @Enumerated(EnumType.STRING)
    @Column(name = "statusAprovacao")
    val statusAprovacao: StatusAprovacaoEnum,
    @Column(name = "segmentoCliente")
    val segmentoCliente: String,
    @Column(name = "tx_login_criador")
    val txLoginCriador: String,
    @ManyToOne
    @JoinColumn(name = "tipo_operacao_id", referencedColumnName = "id")
    val tipoOperacao: TipoOperacaoEntity,
    @ManyToOne
    @JoinColumn(name = "motivo_id", referencedColumnName = "id")
    val motivo: MotivoEntity,
    @ManyToOne
    @JoinColumn(name = "tipo_reversao_id", referencedColumnName = "id")
    val tipoReversao: TipoReversaoEntity,
    @ManyToOne
    @JoinColumn(name = "fila_aprovacao_id", referencedColumnName = "id")
    val filaAprovacao: FilaAprovacaoEntity,
    @OneToMany(mappedBy = "reversao")
    val reversaoAnexoList: List<ReversaoAnexoEntity> = emptyList()
)

fun ReversaoEntity.toDomain() =
    Reversao(
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
        tipoOperacao = tipoOperacao.toDomain(),
        motivo = motivo.toDomain(),
        tipoReversao = tipoReversao.toDomain(),
        filaAprovacao = filaAprovacao.toDomain(),
        txLoginCriador = txLoginCriador
    )

fun Reversao.toEntity() =
    ReversaoEntity(
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
        tipoOperacao = tipoOperacao.toEntity(),
        motivo = motivo.toEntity(),
        tipoReversao = tipoReversao.toEntity(),
        filaAprovacao = filaAprovacao.toEntity(),
        txLoginCriador = txLoginCriador
    )
