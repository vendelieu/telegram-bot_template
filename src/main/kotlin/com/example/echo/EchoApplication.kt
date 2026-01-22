package com.example.echo

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.utils.common.onMessage

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN")

    bot.setFunctionality {
        onMessage {
            message(update.text).send(update.user, bot)
        }
    }

    bot.handleUpdates()
}
