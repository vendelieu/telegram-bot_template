package com.example.heroku

import eu.vendeli.tgbot.TelegramBot
import eu.vendeli.tgbot.api.botactions.setWebhook
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.request.receiveText
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val bot = TelegramBot(System.getenv("TOKEN"), "com.example.heroku.controller")

    setWebhook(System.getenv("HOST") + "/" + System.getenv("TOKEN")).send(bot)

    bot.update.setBehaviour {
        handle(it)
    }

    embeddedServer(Netty, port = System.getenv("PORT").toInt()) {
        routing {
            post("/" + System.getenv("TOKEN")) {
                bot.update.parseAndHandle(call.receiveText())
                call.respond(HttpStatusCode.OK)
            }
        }
    }.start(wait = true)
}
