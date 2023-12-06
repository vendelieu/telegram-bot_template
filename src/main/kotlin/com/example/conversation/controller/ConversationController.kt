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

        bot.inputListener[user] = "name"
    }

    @InputHandler(["name"])
    suspend fun name(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        if (update.text.isBlank()) {
            message {
                "Please say your name, because that's what well-mannered people do :)"
            }.send(user, bot)
            bot.inputListener[user] = "name"
            return
        }

        bot.userData[user, "name"] = update.text

        message { "Oh, ${update.text}, nice to meet you!" }
        message { "How old are you?" }.send(user, bot)

        bot.inputListener[user] = "age"
    }

    @InputHandler(["age"])
    suspend fun age(update: ProcessedUpdate, bot: TelegramBot, user: User) {
        if (update.text.toIntOrNull() == null) {
            message {
                "Perhaps it's not nice to ask your age, but maybe you can tell me anyway."
            }.send(user, bot)

            bot.inputListener[user] = "age"
            return
        }

        val name = bot.userData[user, "name"]
        message {
            "I'm not good at remembering, but I remembered you! " +
                "You're $name and you're ${update.text} years old."
        }.send(user, bot)
    }
}
