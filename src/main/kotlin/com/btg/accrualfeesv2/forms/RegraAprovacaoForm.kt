package com.btg.accrualfeesv2.forms

import com.btg.accrualfeesv2.domains.FilaAprovacao
import com.btg.accrualfeesv2.domains.Perfil
import com.btg.accrualfeesv2.domains.RegraAprovacao
import com.btg.accrualfeesv2.enums.PermissaoEnum

data class RegraAprovacaoForm(
    val permissao: PermissaoEnum,
    val perfilId: Long,
    val filaAprovacaoId: Long
)

fun RegraAprovacaoForm.toDomain(perfil: Perfil, filaAprovacao: FilaAprovacao) =
    RegraAprovacao(
        id = 0,
        permissao = this.permissao,
        perfil = perfil,
        filaAprovacao = filaAprovacao
    )

fun RegraAprovacaoForm.toDomain(perfil: Perfil, filaAprovacao: FilaAprovacao, id: Long) =
    RegraAprovacao(
        id = id,
        permissao = this.permissao,
        perfil = perfil,
        filaAprovacao = filaAprovacao
    )
