import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

fun main() = runBlocking {
    supervisorScope {
        val successful = async {
            delay(500L)
            "Successful result"
        }

        val failing = async<String> {
            delay(200L)
            error("Request failed")
        }

        try {
            failing.await()
        } catch (exception: IllegalStateException) {
            println("Caught failure: ${exception.message}")
        }

        println(successful.await())
    }

    println("Parent finished")
}