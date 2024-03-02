package com.example.heroku.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.ProcessedUpdate

class HelloController {
    @CommandHandler(["/start"])
    suspend fun start(update: ProcessedUpdate, user: User, bot: TelegramBot) {
        message("Hello, it's heroku deployed bot").send(user, bot)
    }
}
