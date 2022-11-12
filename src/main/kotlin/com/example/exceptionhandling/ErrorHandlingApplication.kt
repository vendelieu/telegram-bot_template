package com.example.exceptionhandling

import eu.vendeli.tgbot.TelegramBot

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN", "com.example.exceptionhandling.controller")

    bot.update.setListener {
        handle(it)?.also { ex -> ExceptionHandler.handleException(it, ex) }
        // Handling method returns an exception if there was one.
    }
}
