package com.btg.accrualfeesv2.client.service

import com.btg.accrualfeesv2.client.feing.MotorRegraFeignClient
import com.btg.accrualfeesv2.client.request.MotorRegraRequest
import feign.FeignException.FeignClientException
import org.springframework.stereotype.Component

@Component
class MotorRegraClientService(
    private val motorRegraFeignClient: MotorRegraFeignClient
) {
    
    fun validarRegra(motivo: String, segmento: String): String {
        val request = MotorRegraRequest(
            motivo = motivo,
            segmento = segmento
        )
        return try {
            motorRegraFeignClient.buscarRegra(requestBody = request).body?.name
                ?: throw RuntimeException("Retorno do motor de regra é nulo!")
            
        } catch (ex: FeignClientException) {
            println("TESTE")
            throw RuntimeException("Erro no retorno do motor de regras! Erro = ${ex.message}")
        }
    }
}