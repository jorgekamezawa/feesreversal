package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.Motivo
import jakarta.persistence.*

@Entity
@Table(name = "tb_motivo")
class MotivoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "name")
    val nome: String,
    @ManyToMany(mappedBy = "motivoList")
    val perfilList: List<PerfilEntity> = emptyList(),
    @OneToMany(mappedBy = "motivo")
    val reversaoList: List<ReversaoEntity> = emptyList()
)

fun MotivoEntity.toDomain() =
    Motivo(
        id = this.id,
        nome = this.nome
    )

fun Motivo.toEntity() =
    MotivoEntity(
        id = this.id,
        nome = this.nome
    )
