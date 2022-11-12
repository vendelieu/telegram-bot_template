package com.example.conversation

import eu.vendeli.tgbot.TelegramBot
import org.redisson.Redisson
import org.redisson.config.Config

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN", "com.example.conversation.controller")
    val redis = Redisson.create(Config().apply { useSingleServer().address = "redis://127.0.0.1:6379" })
    bot.userData = BotUserDataImpl(redis)

    bot.handleUpdates()
}
