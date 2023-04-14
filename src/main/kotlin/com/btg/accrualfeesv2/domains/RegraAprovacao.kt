package com.btg.accrualfeesv2.domains

import com.btg.accrualfeesv2.enums.PermissaoEnum

data class RegraAprovacao(
    val id: Long = 0,
    val permissao: PermissaoEnum,
    val perfil: Perfil,
    val filaAprovacao: FilaAprovacao
)
