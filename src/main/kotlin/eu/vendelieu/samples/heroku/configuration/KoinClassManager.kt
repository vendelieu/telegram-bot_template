package eu.vendelieu.samples.heroku.configuration

import eu.vendeli.tgbot.interfaces.ClassManager
import org.koin.core.component.KoinComponent

class KoinClassManager : KoinComponent, ClassManager {
    override fun getInstance(clazz: Class<*>, vararg initParams: Any?): Any = getKoin().get(clazz.kotlin)
}
