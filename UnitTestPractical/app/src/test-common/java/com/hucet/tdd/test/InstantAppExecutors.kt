package com.hucet.tdd.test

import com.hucet.tdd.util.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
    companion object {
        val instant = Executor { it.run() }
    }
}