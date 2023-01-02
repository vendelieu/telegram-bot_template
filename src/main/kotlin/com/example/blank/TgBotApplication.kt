package com.example.blank

import eu.vendeli.tgbot.TelegramBot
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val bot = TelegramBot("BOT_TOKEN", "com.example.blank.controller")
    // And that's all write your own bot from blank.
    // Create new controllers in controller folder or expand old one

    bot.handleUpdates()
}
