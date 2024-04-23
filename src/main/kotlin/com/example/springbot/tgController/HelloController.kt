package com.example.springbot.tgController

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User
import org.springframework.stereotype.Component

@Component // This annotation is necessary for the correct functioning of the spring DI
class HelloController {
    @CommandHandler(["/start"])
    suspend fun start(user: User, bot: TelegramBot) {
        message { "Hello!" }.send(user, bot)
    }
}
