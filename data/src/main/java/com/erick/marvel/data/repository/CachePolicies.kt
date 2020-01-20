package com.erick.marvel.data.repository

import com.erick.marvel.domain.repository.CacheItemPolicy
import com.erick.marvel.domain.repository.CachePolicy
import com.erick.marvel.domain.repository.TimeProvider
import java.util.concurrent.TimeUnit

class CachePolicyTtl(
        ttl: Int,
        timeUnit: TimeUnit,
        private val timeProvider: TimeProvider
) : CachePolicy {

    private val ttlMillis: Long = timeUnit.toMillis(ttl.toLong())

    override fun isValid(cacheItem: CacheItemPolicy): Boolean {
        val lifeTime = cacheItem.timestamp + ttlMillis
        return lifeTime > timeProvider.currentTimeMillis()
    }

    companion object {
        fun oneMinute(timeProvider: TimeProvider): CachePolicyTtl {
            return CachePolicyTtl(ttl = 1, timeUnit = TimeUnit.MINUTES, timeProvider = timeProvider)
        }
    }
}