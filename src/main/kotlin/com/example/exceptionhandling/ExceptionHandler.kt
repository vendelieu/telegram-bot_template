package com.example.exceptionhandling

import eu.vendeli.tgbot.types.internal.ProcessedUpdate
import org.slf4j.LoggerFactory

object ExceptionHandler {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun handleException(update: ProcessedUpdate, ex: Throwable) {
        logger.info("Received an exception while processing an update: $update", ex)
    }
}