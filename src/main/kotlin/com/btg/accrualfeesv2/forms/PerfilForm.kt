package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.Motivo
import com.btg.accrualfeesv2.domains.Perfil

data class PerfilForm(
    val nome: String,
    val motivoIdList: List<Long>
)

fun PerfilForm.toDomain(motivoList: List<Motivo>) =
    Perfil(
        id = 0,
        nome = this.nome,
        motivoList = motivoList
    )

fun PerfilForm.toDomain(motivoList: List<Motivo>, id: Long) =
    Perfil(
        id = id,
        nome = this.nome,
        motivoList = motivoList
    )
