package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.TipoOperacao
import jakarta.persistence.*

@Entity
@Table(name = "tb_tipo_operacao")
class TipoOperacaoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "name")
    val nome: String,
    @Column(name = "typeCode")
    val typeCode: String,
    @Column(name = "reversionTypeCode")
    val reversionTypeCode: String,
    @ManyToOne
    @JoinColumn(name = "tipo_reversao_id", referencedColumnName = "id")
    val tipoReversao: TipoReversaoEntity,
    @OneToMany(mappedBy = "tipoOperacao")
    val reversaoList: List<ReversaoEntity> = emptyList()
)

fun TipoOperacaoEntity.toDomain() =
    TipoOperacao(
        id = this.id,
        nome = this.nome,
        typeCode = this.typeCode,
        reversionTypeCode = this.reversionTypeCode,
        tipoReversao = tipoReversao.toDomain()
    )

fun TipoOperacao.toEntity() =
    TipoOperacaoEntity(
        id = this.id,
        nome = this.nome,
        typeCode = this.typeCode,
        reversionTypeCode = this.reversionTypeCode,
        tipoReversao = tipoReversao.toEntity()
    )
