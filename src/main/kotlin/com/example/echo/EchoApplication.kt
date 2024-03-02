package com.example.echo

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.message.message

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN")

    bot.handleUpdates {
        onMessage {
            message(update.text).send(update.user, bot)
        }
    }
}
