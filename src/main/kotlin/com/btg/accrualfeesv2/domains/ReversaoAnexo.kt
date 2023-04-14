package com.btg.accrualfeesv2.domains

data class ReversaoAnexo(
    val id: Long = 0,
    val s3FileKey: String,
    val reversao: Reversao
)
