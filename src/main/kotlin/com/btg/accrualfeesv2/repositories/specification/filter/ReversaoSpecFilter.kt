package com.btg.accrualfeesv2.repositories.specification.filter

import com.btg.accrualfeesv2.entities.FilaAprovacaoEntity
import com.btg.accrualfeesv2.enums.StatusAprovacaoEnum

data class ReversaoSpecFilter(
    val statusAprovacaoList: List<StatusAprovacaoEnum>? = null,
    val filaAprovacaoList: List<FilaAprovacaoEntity>? = null
)