package com.btg.accrualfeesv2.client.feing

import com.btg.accrualfeesv2.client.MotorRegraResponse
import com.btg.accrualfeesv2.client.request.MotorRegraRequest
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "motor-regras-client", url = "http://localhost:8081/validate_rule/accrualfees")
interface MotorRegraFeignClient {
    
    @PostMapping
    fun buscarRegra(
        @RequestHeader origem: String = "AccrualFees",
        @RequestBody requestBody: MotorRegraRequest
    ): ResponseEntity<MotorRegraResponse>
}