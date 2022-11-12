package com.example.exceptionhandling.controller

import eu.vendeli.tgbot.annotations.CommandHandler

class ExceptionThrowingController {
    @CommandHandler(["/start"])
    fun start() {
        throw RuntimeException()
    }
}