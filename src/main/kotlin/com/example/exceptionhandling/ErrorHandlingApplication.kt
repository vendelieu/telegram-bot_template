package com.example.exceptionhandling

import eu.vendeli.tgbot.TelegramBot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val bot = TelegramBot("BOT_TOKEN", "com.example.exceptionhandling.controller")

    launch(Dispatchers.Unconfined) {
        bot.update.caughtExceptions.consumeEach {
            ExceptionHandler.handleException(it.second, it.first)
        }
    }
    bot.handleUpdates()
}