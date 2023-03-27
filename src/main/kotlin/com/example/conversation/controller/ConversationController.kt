package com.example.conversation.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.annotations.InputHandler
import eu.vendeli.tgbot.api.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.MessageUpdate
import eu.vendeli.tgbot.types.internal.ProcessedUpdate

class ConversationController {
    @CommandHandler(["/start"])
    suspend fun start(user: User, bot: TelegramBot) {
        message { "Hello, my name is Adam, and you?" }.send(user, bot)

        bot.inputListener.set(user.id, "name")
    }

    @InputHandler(["name"])
    suspend fun name(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        if (update !is MessageUpdate) {
            message { "Please say your name, because that's what well-mannered people do :)" }.send(user, bot)
            bot.inputListener.set(user.id, "name")
        }

        bot.userData.set(user.id, "name", update.text)

        message { "Oh, ${update.text}, nice to meet you!" }
        message { "How old are you?" }.send(user, bot)

        bot.inputListener.set(user.id, "age")
    }

    @InputHandler(["age"])
    suspend fun age(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        if (update !is MessageUpdate || update.text.toIntOrNull() == null) {
            message { "Perhaps it's not nice to ask your age, but maybe you can tell me anyway." }
                .send(user, bot)
            bot.inputListener.set(user.id, "age")
        }

        val name = bot.userData.get<String>(user.id, "name")
        message {
            "I'm not good at remembering, but I remembered you! You're $name and you're ${update.text} years old."
        }.send(user, bot)
    }
}
