package com.example.springbot.implementation

import eu.vendeli.tgbot.interfaces.ClassManager
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import kotlin.reflect.KClass

@Component
class SpringClassManager(
    private val applicationContext: ApplicationContext
) : ClassManager {
    override fun getInstance(kClass: KClass<*>, vararg initParams: Any?): Any =
        applicationContext.getBean(kClass.java, initParams)
}