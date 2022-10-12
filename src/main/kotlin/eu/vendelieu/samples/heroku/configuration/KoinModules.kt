package eu.vendelieu.samples.heroku.configuration

import eu.vendelieu.samples.heroku.controller.HelloController
import org.koin.dsl.module

object KoinModules {
    fun getControllersModule() = module {
        single { HelloController() }
    }
}
