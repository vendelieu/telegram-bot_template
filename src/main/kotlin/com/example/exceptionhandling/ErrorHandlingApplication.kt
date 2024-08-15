package com.example.exceptionhandling

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.utils.runExceptionHandler
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val bot = TelegramBot("BOT_TOKEN")

    bot.update.runExceptionHandler { e, upd ->
        ExceptionHandler.handleException(upd, e)
    }
    bot.handleUpdates()
}