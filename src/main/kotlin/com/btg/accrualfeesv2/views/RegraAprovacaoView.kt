package com.btg.accrualfeesv2.views

import com.btg.accrualfeesv2.domains.RegraAprovacao
import com.btg.accrualfeesv2.enums.PermissaoEnum

data class RegraAprovacaoView(
    val id: Long,
    val permissao: PermissaoEnum,
    val perfil: PerfilView,
    val filaAprovacao: FilaAprovacaoView
)

fun RegraAprovacao.toView() =
    RegraAprovacaoView(
        id = this.id,
        permissao = permissao,
        perfil = perfil.toView(),
        filaAprovacao = filaAprovacao.toView()
    )
