package com.btg.accrualfeesv2.aws.sqs.service

import org.springframework.stereotype.Service
import software.amazon.awssdk.services.sqs.SqsClient
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest
import software.amazon.awssdk.services.sqs.model.SendMessageRequest

@Service
class SqsService(
    private val sqsClient: SqsClient
) {
    
    fun sendMessage(queueName: String?, mensagem: String) {
        val getQueueRequest = GetQueueUrlRequest.builder()
            .queueName(queueName)
            .build()
        val queueUrl = sqsClient.getQueueUrl(getQueueRequest).queueUrl()
        val sendMsgRequest = SendMessageRequest.builder()
            .queueUrl(queueUrl)
            .messageBody(mensagem)
            .delaySeconds(5)
            .build()
        sqsClient.sendMessage(sendMsgRequest)
        println("Mensagem Enviada com Sucesso! $mensagem")
    }
}