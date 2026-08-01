import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.seconds

suspend fun fetchMessage(
    messageId: MessageId,
): String {
    delay(1.seconds)
    return "Message: ${messageId.value}"
}


fun main() = runBlocking {
    println("Before fetch")

    val message = fetchMessage(
        MessageId.from("message-123"),
    )

    println(message)
    println("After fetch")
}