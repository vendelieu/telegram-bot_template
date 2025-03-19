package com.example.springbot.configuration

import eu.vendeli.spring.starter.BotConfiguration
import eu.vendeli.tgbot.types.component.LogLvl
import eu.vendeli.tgbot.utils.common.BotConfigurator
import org.springframework.context.annotation.Configuration

@Configuration
class BotConfig : BotConfiguration() {
    override fun applyCfg(): BotConfigurator = {
        logging {
            botLogLevel = LogLvl.TRACE
        }
    }
}