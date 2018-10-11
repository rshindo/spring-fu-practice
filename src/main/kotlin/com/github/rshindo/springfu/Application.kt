package com.github.rshindo.springfu

import org.springframework.fu.application
import org.springframework.fu.module.logging.LogLevel
import org.springframework.fu.module.logging.level
import org.springframework.fu.module.logging.logback.consoleAppender
import org.springframework.fu.module.logging.logback.logback
import org.springframework.fu.module.logging.logging
import org.springframework.fu.module.webflux.netty.netty
import org.springframework.fu.module.webflux.webflux

val app = application {
    logging {
        level(LogLevel.INFO)
        logback {
            consoleAppender()
        }
    }
    webflux {
        val port = if (profiles.contains("test")) 8181 else 8080
        server(netty(port)) {
            router {
                GET("/") {
                    ok().syncBody("Hello World!")
                }
            }
        }
    }
}

fun main(args: Array<String>) {
    app.run(await = true)
}