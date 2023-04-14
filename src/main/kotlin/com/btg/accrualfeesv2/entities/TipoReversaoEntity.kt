package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.TipoReversao
import jakarta.persistence.*

@Entity
@Table(name = "tb_tipo_reversao")
class TipoReversaoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "name")
    val nome: String,
    @OneToMany(mappedBy = "tipoReversao")
    val tipoOperacaoList: List<TipoOperacaoEntity> = emptyList(),
    @OneToMany(mappedBy = "tipoReversao")
    val reversaoList: List<ReversaoEntity> = emptyList()
)

fun TipoReversaoEntity.toDomain() =
    TipoReversao(
        id = this.id,
        nome = this.nome
    )

fun TipoReversao.toEntity() =
    TipoReversaoEntity(
        id = this.id,
        nome = this.nome
    )
