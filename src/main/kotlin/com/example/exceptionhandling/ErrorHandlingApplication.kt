package com.example.exceptionhandling

import eu.vendeli.tgbot.TelegramBot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val bot = TelegramBot("BOT_TOKEN", "com.example.exceptionhandling.controller")

    launch(Dispatchers.Unconfined) {
        for (e in bot.update.caughtExceptions) {
            ExceptionHandler.handleException(e.update, e.exception)
            delay(100)
        }
    }
    bot.handleUpdates()
}