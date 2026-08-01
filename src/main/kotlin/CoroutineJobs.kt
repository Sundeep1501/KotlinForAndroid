import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { index ->
                delay(500L)
                println("Step ${index + 1}")
            }
        } finally {
            println("Cleaning up")
        }
    }

    delay(1_100L)
    println("Cancelling")

    // join waits for the job to be completed.
    job.cancelAndJoin()

    println("Finished")
}