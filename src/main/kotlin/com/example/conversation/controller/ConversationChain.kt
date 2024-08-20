package com.example.conversation.controller

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.annotations.InputChain
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.generated.getAllState
import eu.vendeli.tgbot.types.User
import eu.vendeli.tgbot.types.internal.BreakCondition
import eu.vendeli.tgbot.types.internal.ChainLink
import eu.vendeli.tgbot.types.internal.ProcessedUpdate
import eu.vendeli.tgbot.types.internal.chain.BaseStatefulLink

@InputChain
object ConversationChain {
    object Name : BaseStatefulLink() {
        override val breakCondition = BreakCondition { _, update, _ -> update.text.isBlank() }
        override suspend fun breakAction(user: User, update: ProcessedUpdate, bot: TelegramBot) {
            message {
                "Please say your name, because that's what well-mannered people do :)"
            }.send(user, bot)
        }

        override suspend fun action(user: User, update: ProcessedUpdate, bot: TelegramBot): String {
            message { "Oh, ${update.text}, nice to meet you!" }.inlineKeyboardMarkup {

            }
            message { "How old are you?" }.send(user, bot)

            return update.text
        }
    }

    object Age : BaseStatefulLink() {
        override val breakCondition = BreakCondition { _, update, _ -> update.text.toIntOrNull() == null }
        override suspend fun breakAction(user: User, update: ProcessedUpdate, bot: TelegramBot) {
            message {
                "Perhaps it's not nice to ask your age, but maybe you can tell me anyway."
            }.send(user, bot)
        }

        override suspend fun action(user: User, update: ProcessedUpdate, bot: TelegramBot): String {
            message { "Pleased to meet you!" }.send(user, bot)

            return update.text
        }
    }

    object Final : ChainLink() {
        override suspend fun action(user: User, update: ProcessedUpdate, bot: TelegramBot) {
            val state = user.getAllState(ConversationChain)

            message {
                "I'm not good at remembering, but I remembered you! " +
                        "You're ${state.Name} and you're ${state.Age} years old."
            }.send(user, bot)
        }
    }
}