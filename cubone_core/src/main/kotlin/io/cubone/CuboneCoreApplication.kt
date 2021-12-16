package io.cubone

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CuboneCoreApplication

fun main(args: Array<String>) {
	runApplication<CuboneCoreApplication>(*args)
}
