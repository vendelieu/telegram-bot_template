package eu.vendeli.samples

import eu.vendeli.tgbot.TelegramBot

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN", "eu.vendeli.samples")
    // Write your own bot from scratch.

    bot.handleUpdates()
}
