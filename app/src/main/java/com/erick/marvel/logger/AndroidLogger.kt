package com.erick.marvel.logger

import android.util.Log
import com.erick.marvel.domain.logger.LogLevel
import com.erick.marvel.domain.logger.LoggerTree

class AndroidLogger : LoggerTree {
    override fun log(tag: () -> String, level: LogLevel, msg: () -> String, throwable: Throwable?) {
        when (level) {
            LogLevel.ERROR -> Log.e(tag(), msg(), throwable)
            LogLevel.WARN -> Log.w(tag(), msg())
            LogLevel.INFO -> Log.i(tag(), msg())
            LogLevel.DEBUG -> Log.d(tag(), msg())
            LogLevel.VERBOSE -> Log.v(tag(), msg())
        }
    }
}