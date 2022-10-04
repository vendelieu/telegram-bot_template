package eu.vendeli.springbot.implementation

import eu.vendeli.tgbot.interfaces.ClassManager
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class SpringClassManager(
    private val applicationContext: ApplicationContext
) : ClassManager {
    override fun getInstance(clazz: Class<*>, vararg initParams: Any?): Any =
        applicationContext.getBean(clazz, initParams)
}