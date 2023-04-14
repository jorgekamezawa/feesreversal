package com.btg.accrualfeesv2

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class Accrualfeesv2Application

fun main(args: Array<String>) {
	runApplication<Accrualfeesv2Application>(*args)
}
