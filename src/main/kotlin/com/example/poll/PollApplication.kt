package com.example.poll

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.utils.common.onMessage

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN")

    bot.setFunctionality {
        onMessage {
            println("\uD83D\uDC40")
        }
    }

    bot.handleUpdates()
}
