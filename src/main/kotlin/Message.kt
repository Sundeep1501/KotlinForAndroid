data class Message(
    val id: String,
    val senderName: String,
    val sentAtMillis: Long,
)

data class SenderSummary(
    val senderName: String,
    val messageCount: Int,
    val latestMessageMillis: Long,
)

fun summarizeBySender(
    messages: List<Message>,
): List<SenderSummary> {
    return messages.groupBy { it.senderName.lowercase() }.map { (key, messages) ->
        SenderSummary(
            senderName = key, messageCount = messages.size, latestMessageMillis = messages.maxOf { it.sentAtMillis })
    }.sortedWith(
        compareByDescending<SenderSummary> { it.messageCount }.thenBy { it.senderName }
    )
}