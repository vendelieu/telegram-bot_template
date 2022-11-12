package com.example.heroku.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.types.internal.ProcessedUpdate

class HelloController {
    @CommandHandler(["/start"])
    suspend fun start(update: ProcessedUpdate, bot: TelegramBot) {
        message("Hello, it's heroku deployed bot").send(update.user, bot)
    }
}