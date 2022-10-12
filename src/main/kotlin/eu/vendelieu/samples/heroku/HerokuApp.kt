package eu.vendelieu.samples.heroku

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.botactions.setWebhook
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

suspend fun main() {
    val bot = TelegramBot(System.getenv("TOKEN"), "eu.vendelieu.samples.heroku.controller")

    setWebhook(System.getenv("HOST") + "/" + System.getenv("TOKEN")).send(bot)

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
