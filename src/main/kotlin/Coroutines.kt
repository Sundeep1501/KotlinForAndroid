import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

suspend fun fetchMessage(
    messageId: MessageId,
    delayMillis: Long,
): String {
    delay(delayMillis.milliseconds)
    return "Message: ${messageId.value}"
}


fun main() = runBlocking {
    launch {
        val message = fetchMessage(
            messageId = MessageId.from("message-1"),
            delayMillis = 1_000L
        )
        println(message)
    }

    launch {
        val message = fetchMessage(
            messageId = MessageId.from("message-2"),
            delayMillis = 500L,
        )
        println(message)
    }


    println("Requests started")
}