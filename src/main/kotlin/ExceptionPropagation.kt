import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    try {
        coroutineScope {
            launch {
                try {
                    delay(1_000L)
                    println("First child completed")
                } finally {
                    println("First child cleanup")
                }
            }

            launch {
                delay(200L)
                println("Second child failing")
                error("Network failure")
            }
        }
    } catch (exception: IllegalStateException) {
        println("Caught: ${exception.message}")
    }

    println("Parent finished")
}