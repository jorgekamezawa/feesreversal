package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.Perfil

data class PerfilView(
    val id: Long,
    val nome: String,
    val motivoList: List<MotivoView>
)

fun Perfil.toView() =
    PerfilView(
        id = this.id,
        nome = this.nome,
        motivoList = motivoList.map { it.toView() }
    )
