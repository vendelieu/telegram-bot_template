package com.example.conversation

import eu.vendeli.tgbot.TelegramBot

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN", "com.example.conversation")

    bot.handleUpdates()
}
