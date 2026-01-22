package com.example.conversation.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User

class ConversationController {
    @CommandHandler(["/start"])
    suspend fun start(user: User, bot: TelegramBot) {
        message { "Hello, would you like to talk?" }.inlineKeyboardMarkup {
            "yes" callback "conversation"
        }.send(user, bot)
    }
}
