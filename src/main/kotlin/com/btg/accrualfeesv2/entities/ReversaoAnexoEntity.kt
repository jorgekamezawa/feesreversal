package com.btg.accrualfeesv2.entities

import com.btg.accrualfeesv2.domains.ReversaoAnexo
import jakarta.persistence.*

@Entity
@Table(name = "tb_reversao_anexo")
class ReversaoAnexoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "s3_file_key")
    val s3FileKey: String,
    @ManyToOne
    @JoinColumn(name = "reversao_id", referencedColumnName = "id")
    val reversao: ReversaoEntity
)

fun ReversaoAnexoEntity.toDomain() =
    ReversaoAnexo(
        id = this.id,
        s3FileKey = this.s3FileKey,
        reversao = this.reversao.toDomain()
    )

fun ReversaoAnexo.toEntity() =
    ReversaoAnexoEntity(
        id = this.id,
        s3FileKey = this.s3FileKey,
        reversao = this.reversao.toEntity()
    )
