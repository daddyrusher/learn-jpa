package com.daddyrusher.postgresjparestapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class PostgresJpaRestApiApp

fun main(args: Array<String>) {
    runApplication<PostgresJpaRestApiApp>(*args)
}
