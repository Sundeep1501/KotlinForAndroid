import kotlinx.coroutines.*


fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        var iterations = 0L

        try {
            while (true) {
                currentCoroutineContext().ensureActive()
                iterations++
            }
        } finally {
            println("Stopped after $iterations iterations")
        }
    }

//    delay(100L)
    println("Cancelling")

    job.cancelAndJoin()

    println("Finished")
}