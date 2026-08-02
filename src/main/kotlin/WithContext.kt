import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

suspend fun loadMessageFromBlockingSource(
    messageId: MessageId,
): String =
    withContext(Dispatchers.IO) {
        Thread.sleep(500L)
        "Message: ${messageId.value}"
    }

fun main() = runBlocking {
    println("Before: ${Thread.currentThread().name}")

    val message = loadMessageFromBlockingSource(
        MessageId.from("message-123"),
    )

    println(message)
    println("After: ${Thread.currentThread().name}")
}