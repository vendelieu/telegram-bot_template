package com.example.conversation.controller

import eu.vendeli.ktnip.utils.cast
import eu.vendeli.tgbot.annotations.WizardHandler
import eu.vendeli.tgbot.api.answer.answerCallbackQuery
import eu.vendeli.tgbot.api.message.message
import eu.vendeli.tgbot.generated.getState
import eu.vendeli.tgbot.types.chain.Transition
import eu.vendeli.tgbot.types.chain.WizardContext
import eu.vendeli.tgbot.types.chain.WizardStep
import eu.vendeli.tgbot.types.component.CallbackQueryUpdate
import eu.vendeli.tgbot.types.component.UpdateType

@WizardHandler(["conversation"], scope = [UpdateType.CALLBACK_QUERY])
class ProfileWizard {
    object Name : WizardStep() {
        override suspend fun onEntry(ctx: WizardContext) {
            answerCallbackQuery(ctx.update.cast<CallbackQueryUpdate>().callbackQuery.id).options {
                text = "Yaaaay~~!"
            }.send(ctx.user, ctx.bot)

            message { "Let's get to know each other better, what's your name?" }.send(ctx.user, ctx.bot)
        }

        override suspend fun onRetry(ctx: WizardContext) {
            message {
                "Please say your name, because that's what well-mannered people do :)"
            }.send(ctx.user, ctx.bot)
        }

        override suspend fun validate(ctx: WizardContext): Transition {
            return if (ctx.update.text.trim().isBlank()) {
                Transition.Retry
            } else {
                Transition.Next
            }
        }

        override suspend fun store(ctx: WizardContext): String = ctx.update.text
    }

    object Age : WizardStep() {
        override suspend fun onEntry(ctx: WizardContext) {
            message { "Pleased to meet you!" }.send(ctx.user, ctx.bot)
            message { "How old are you?" }.send(ctx.user, ctx.bot)
        }

        override suspend fun validate(ctx: WizardContext): Transition {
            return if (ctx.update.text.toIntOrNull() == null) {
                Transition.Retry
            } else {
                Transition.Next
            }
        }

        override suspend fun onRetry(ctx: WizardContext) {
            message {
                "Perhaps it's not nice to ask your age, but maybe you can tell me anyway."
            }.send(ctx.user, ctx.bot)
        }

        override suspend fun store(ctx: WizardContext): Int = ctx.update.text.toInt()
    }

    object Final : WizardStep() {
        override suspend fun onEntry(ctx: WizardContext) {
            val name = ctx.getState<Name>()
            val age = ctx.getState<Age>()

            message {
                "I'm not good at remembering, but I remembered you! " +
                        "You're $name and you're $age years old."
            }.send(ctx.user, ctx.bot)
        }
    }
}
