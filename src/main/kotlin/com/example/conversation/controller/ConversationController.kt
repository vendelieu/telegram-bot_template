package com.example.conversation.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.utils.setChain

class ConversationController {
    @CommandHandler(["/start"])
    suspend fun start(user: User, bot: TelegramBot) {
        message { "Hello, my name is Adam, and you?" }.send(user, bot)

        bot.inputListener.setChain(user = user, firstLink = ConversationChain.Name)
    }
}
