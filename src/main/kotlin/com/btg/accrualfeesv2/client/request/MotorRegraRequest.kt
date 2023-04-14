package com.btg.accrualfeesv2.client.request

import java.math.BigDecimal

data class MotorRegraRequest(
    val motivo: String,
    val segmento: String,
    val valorInvestido: BigDecimal? = null
)
