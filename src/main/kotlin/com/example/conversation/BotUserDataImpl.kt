package com.example.conversation

import eu.vendeli.tgbot.interfaces.BotUserData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.future.asDeferred
import kotlinx.coroutines.withContext
import org.redisson.api.LocalCachedMapOptions
import org.redisson.api.RedissonClient

class BotUserDataImpl(redis: RedissonClient) : BotUserData {
    private val cache = redis.getLocalCachedMap<String, String>(
        "telegramBot-userData", LocalCachedMapOptions.defaults()
    )

    override fun del(telegramId: Long, key: String) {
        cache.fastRemoveAsync("${telegramId}_$key")
    }

    override suspend fun delAsync(telegramId: Long, key: String): Deferred<Boolean> = coroutineScope {
        async {
            withContext(Dispatchers.IO) {
                cache.fastRemoveAsync("${telegramId}_$key").get()
            } > 0
        }
    }

    override fun <T> get(telegramId: Long, key: String): T? = cache["${telegramId}_$key"] as? T

    override suspend fun <T> getAsync(telegramId: Long, key: String): Deferred<T?> =
        cache.getAsync("${telegramId}_$key").asDeferred() as Deferred<T?>

    override fun set(telegramId: Long, key: String, value: Any?) {
        cache.fastPutAsync("${telegramId}_$key", value.toString())
    }

    override suspend fun setAsync(telegramId: Long, key: String, value: Any?): Deferred<Boolean> =
        cache.fastPutAsync("${telegramId}_$key", value.toString()).asDeferred()
}