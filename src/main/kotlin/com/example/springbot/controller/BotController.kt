package com.example.springbot.controller

import eu.vendeli.tgbot.TelegramBot
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BotController(
    private val telegramBot: TelegramBot
) {
    @PostMapping("/webhook")
    suspend fun webhook(@RequestBody update: String) {
        telegramBot.update.parseAndHandle(update)
    }
}
