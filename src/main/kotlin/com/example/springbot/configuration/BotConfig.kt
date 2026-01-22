package com.example.springbot.configuration

import ch.qos.logback.classic.Level
import eu.vendeli.spring.starter.BotConfiguration
import eu.vendeli.tgbot.utils.common.BotConfigurator
import eu.vendeli.tgbot.utils.common.logLevel
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig : BotConfiguration() {
    override fun applyCfg(): BotConfigurator = {
        logLevel = Level.INFO
    }
}