package com.example.echo

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.message

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN")

    bot.handleUpdates {
        onMessage {
            message(data.text ?: "").send(data.from?.id ?: 0, bot)
        }
    }
}
