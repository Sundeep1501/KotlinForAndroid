import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking

suspend fun fetchConversationSummary(): String =
    coroutineScope {
        // Start both fetches concurrently
        val first: Deferred<String> = async {
            fetchMessage(
                messageId = MessageId.from("message-1"),
                delayMillis = 1_000L
            )
        }

        val second: Deferred<String> = async {
            fetchMessage(
                messageId = MessageId.from("message-2"),
                delayMillis = 500L
            )
        }
        // Await both results
        val firstValue = first.await()
        val secondValue = second.await()

        // Combine them
        "$firstValue | $secondValue"
    }

fun main() = runBlocking {
    println(fetchConversationSummary())
}