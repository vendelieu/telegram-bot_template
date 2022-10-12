package eu.vendelieu.samples.heroku

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.botactions.setWebhook
import eu.vendelieu.samples.heroku.configuration.KoinClassManager
import eu.vendelieu.samples.heroku.configuration.KoinModules
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.core.context.GlobalContext.startKoin

suspend fun main() {
    val bot = TelegramBot(
        System.getenv("TOKEN"),
        "eu.vendelieu.samples.heroku.controller",
        classManager = KoinClassManager()
    )

    setWebhook(System.getenv("HOST") + "/" + System.getenv("TOKEN")).send(bot)

    startKoin { modules(KoinModules.getControllersModule()) }

    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        routing {
            post("/" + System.getenv("TOKEN")) {
                bot.update.apply {
                    parseUpdate(call.receive())?.handle()
                }
                call.respond(HttpStatusCode.OK)
            }
        }
    }.start(wait = true)
}
