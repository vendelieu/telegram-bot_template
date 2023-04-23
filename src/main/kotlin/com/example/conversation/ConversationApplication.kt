package com.example.conversation

import eu.vendeli.tgbot.TelegramBot
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val bot = TelegramBot("BOT_TOKEN", "com.example.conversation.controller")

    bot.handleUpdates()
}
