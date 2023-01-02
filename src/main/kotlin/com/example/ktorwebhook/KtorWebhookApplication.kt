package com.example.ktorwebhook

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

suspend fun main() {
    val bot = TelegramBot("BOT_TOKEN", "com.example.ktorwebhook.controller")

    setWebhook("https://0.0.0.0/BOT_TOKEN").send(bot)

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            post("/BOT_TOKEN") {
                bot.update.parseAndHandle(call.receiveText())
                call.respond(HttpStatusCode.OK)
            }
        }
    }.start(wait = true)
}
