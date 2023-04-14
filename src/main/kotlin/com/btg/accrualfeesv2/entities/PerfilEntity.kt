package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.Perfil
import jakarta.persistence.*

@Entity
@Table(name = "tb_perfil")
class PerfilEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "name")
    val nome: String,
    @ManyToMany
    @JoinTable(
        name = "tb_perfil_motivo",
        joinColumns = [JoinColumn(name = "perfil_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "motivo_id", referencedColumnName = "id")]
    )
    val motivoList: List<MotivoEntity> = emptyList(),
    @OneToMany(mappedBy = "perfil")
    val regraAprovacaoList: List<RegraAprovacaoEntity> = emptyList()
)

fun PerfilEntity.toDomain() =
    Perfil(
        id = this.id,
        nome = this.nome,
        motivoList = this.motivoList.map { it.toDomain() }
    )

fun Perfil.toEntity() =
    PerfilEntity(
        id = this.id,
        nome = this.nome,
        motivoList = this.motivoList.map { it.toEntity() }
    )
