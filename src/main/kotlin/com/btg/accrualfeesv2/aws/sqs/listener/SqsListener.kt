package com.btg.accrualfeesv2.aws.sqs.listener

import com.btg.accrualfeesv2.repositories.ReversaoRepository
import com.btg.accrualfeesv2.repositories.TipoReversaoRepository
import org.springframework.messaging.Message
import org.springframework.stereotype.Component

@Component
class SqsListener(
    private val reversaoRepository: ReversaoRepository
) {
    
    fun listen(message: Message<Any?>) {
        val payload = message.payload
        val headers = message.headers
        println("PAYLOAD -> $payload")
        println("HEADERS -> $headers")
        val uuid = headers.id
        val contenttype = headers["Sqs_Ma_contentType"]
        val url = headers["Sqs_QueueUrl"]
        val valores: Collection<Any> = headers.values
        println("MESSAGE -> $message")
    }
}