package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.FilaAprovacao
import jakarta.persistence.*

@Entity
@Table(name = "tb_fila_aprovacao")
class FilaAprovacaoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "nome")
    val nome: String,
    @Column(name = "nome_regra")
    val nomeRegra: String,
    @OneToMany(mappedBy = "filaAprovacao")
    val regraAprovacaoList: List<RegraAprovacaoEntity> = emptyList(),
    @OneToMany(mappedBy = "filaAprovacao")
    val reversaoList: List<ReversaoEntity> = emptyList()
)

fun FilaAprovacaoEntity.toDomain() =
    FilaAprovacao(
        id = this.id,
        nome = this.nome,
        nomeRegra = this.nomeRegra
    )

fun FilaAprovacao.toEntity() =
    FilaAprovacaoEntity(
        id = this.id,
        nome = this.nome,
        nomeRegra = this.nomeRegra
    )
