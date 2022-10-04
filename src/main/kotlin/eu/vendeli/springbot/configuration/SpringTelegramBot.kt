package eu.vendeli.springbot.configuration

import eu.vendeli.springbot.implementation.SpringClassManager
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
    fun instance() = TelegramBot(
        botToken,
        "eu.vendeli.springbot.botController",
        classManager = springClassManager
    ).apply {
        // .. configure context, like userData etc
    }
}