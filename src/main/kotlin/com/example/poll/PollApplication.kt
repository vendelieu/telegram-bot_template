package com.example.poll

import eu.vendeli.tgbot.TelegramBot

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN", "com.example.poll.controller")

    bot.update.setListener {
        handle(it)
        handle(it) {
            onPollAnswer {
                println("User#${update.message?.from?.id} chose ${data.optionIds}")
            }
        }
    }
}
