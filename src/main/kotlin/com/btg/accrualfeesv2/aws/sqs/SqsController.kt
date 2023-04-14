package com.btg.accrualfeesv2.aws.sqs

import com.btg.accrualfeesv2.aws.sqs.service.SqsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/sqs")
class SqsController(
    private val sqsService: SqsService
) {
    
    @PostMapping("/{queue_name}")
    fun sendMessage(
        @PathVariable("queue_name") queueName: String,
        @RequestBody mensagem: String
    ): ResponseEntity<Any> {
        sqsService.sendMessage(queueName, mensagem)
        return ResponseEntity.ok().build()
    }
}