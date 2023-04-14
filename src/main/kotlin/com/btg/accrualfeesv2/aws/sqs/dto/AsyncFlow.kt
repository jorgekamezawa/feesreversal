package com.btg.accrualfeesv2.aws.sqs.dto

data class AsyncFlow(
    val order: Int = 1,
    val stepSystemId: String,
    val operation: String,
    val genericData: String? = null,
    val dataFlow: String = "{\"sourceAccount\": {\"agency\": \"1\", \"account\": \"1047885\", \"documentId\": \"12925338704\", \"accountType\": null}, \"amountToTransfer\": \"100\", \"destinationAccount\": null}",
    val callback: String = "{\"sourceAccount\": {\"agency\": \"1\", \"account\": \"1047885\", \"documentId\": \"12925338704\", \"accountType\": null}, \"amountToTransfer\": \"100\", \"destinationAccount\": null, \"AuthorizationId\": \"e8e64410744246beadf11d4e0224aaba\"}",
    val result: ResultCallback
)
