package eu.vendeli.springbot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SpringBotApplication

fun main(args: Array<String>) {
    SpringApplication.run(SpringBotApplication::class.java, *args)
}
