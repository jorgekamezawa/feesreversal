package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.RegraAprovacao
import com.btg.accrualfeesv2.enums.PermissaoEnum
import jakarta.persistence.*

@Entity
@Table(name = "tb_regra_aprovacao")
class RegraAprovacaoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Enumerated(EnumType.STRING)
    @Column(name = "permissao")
    val permissao: PermissaoEnum,
    @ManyToOne
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    val perfil: PerfilEntity,
    @ManyToOne
    @JoinColumn(name = "fila_aprovacao_id", referencedColumnName = "id")
    val filaAprovacao: FilaAprovacaoEntity
)

fun RegraAprovacaoEntity.toDomain() =
    RegraAprovacao(
        id = this.id,
        permissao = this.permissao,
        perfil = perfil.toDomain(),
        filaAprovacao = filaAprovacao.toDomain()
    )

fun RegraAprovacao.toEntity() =
    RegraAprovacaoEntity(
        id = this.id,
        permissao = this.permissao,
        perfil = perfil.toEntity(),
        filaAprovacao = filaAprovacao.toEntity()
    )
