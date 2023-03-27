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

const val HOST = "example.com" // change host to yours
const val TOKEN = "BOT_TOKEN" // put token there

suspend fun main() {
    val bot = TelegramBot(TOKEN, "com.example.ktorwebhook.controller")

    setWebhook("https://$HOST/$TOKEN").send(bot)

    bot.update.setBehaviour { handle(it) }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        routing {
            post("/$TOKEN") {
                bot.update.parseAndHandle(call.receiveText())
                call.respond(HttpStatusCode.OK)
            }
        }
    }.start(wait = true)
}
