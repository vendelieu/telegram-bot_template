package com.example.conversation

import eu.vendeli.tgbot.TelegramBot
import org.redisson.Redisson
import org.redisson.config.Config

suspend fun main() {
    val redis = Redisson.create(Config().apply { useSingleServer().address = "redis://127.0.0.1:6379" })
    val bot = TelegramBot("BOT_TOKEN", "com.example.conversation.controller") {
        context {
            userData = BotUserDataImpl(redis)
        }
    }

    bot.handleUpdates()
}
