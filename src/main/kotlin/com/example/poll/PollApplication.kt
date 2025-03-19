package com.example.poll

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.utils.common.onPollAnswer

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN")

    bot.update.setListener {
        handle(it)
        handle(it) {
            onPollAnswer {
                println("User#${update.user?.id} chose ${update.pollAnswer.optionIds}")
            }
        }
    }
}
