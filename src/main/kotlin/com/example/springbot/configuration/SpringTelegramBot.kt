package com.example.springbot.configuration

import com.example.springbot.implementation.SpringClassManager
import eu.vendeli.tgbot.TelegramBot
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringTelegramBot(
    @Value("\${bot-token}")
    private val botToken: String,
    private val springClassManager: SpringClassManager
) {
    @Bean
    fun instance(): TelegramBot {
        val bot = TelegramBot(botToken, "com.example.springbot.botController") {
            classManager = springClassManager
            // .. configure context, like userData etc
        }

        // configure handling behaviour for webhook handling
        bot.update.setBehaviour {
            handle(it)
        }

        return bot
    }
}