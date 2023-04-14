package com.btg.accrualfeesv2.controllers.handler.view

data class ErrorView(
    val httpStatus: String,
    val message: String?,
    val dateTime: String
)
