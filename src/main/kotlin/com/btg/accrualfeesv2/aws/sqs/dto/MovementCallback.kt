package com.btg.accrualfeesv2.aws.sqs.dto

import java.util.*

data class MovementCallback(
    val externalId: String = UUID.randomUUID().toString(),
    val documentId: String,
    val asyncFlow: List<AsyncFlow>,
    val correlationId: String,
    val status: String,
    val errorMessage: String,
    val origin: String,
    val product: String,
)
