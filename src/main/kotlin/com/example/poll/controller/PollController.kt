package com.example.poll.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.CommandHandler
import eu.vendeli.tgbot.api.common.poll
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.poll.PollType

class PollController {
    @CommandHandler(["/start"])
    suspend fun start(user: User, bot: TelegramBot) {
        message { "Hello!" }.send(user, bot)

        poll("Zebra white with black stripes or black with white stripes") {
            option { "white with black stripes" }
            option { "black with with white stripes" }
        }.options {
            type = PollType.Quiz
        }.send(user, bot)

        message { "Want more polls?" }.inlineKeyboardMarkup {
            "yes" callback "morePolls"
        }.send(user.id, bot)
    }

    @CommandHandler.CallbackQuery(["morePolls"])
    suspend fun anotherPoll(user: User, bot: TelegramBot) {
        poll("What color is the dress?") {
            option { "Blue-black" }
            option { "White-gold" }
        }.options {
            allowsMultipleAnswers = true
            isClosed = true
            explanation = "Who gave this man a time machine?"
        }.send(user, bot)
    }
}
