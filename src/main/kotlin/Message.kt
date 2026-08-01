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
    return messages.groupBy { it.senderName.lowercase() }.map { (senderName, senderMessages) ->
        SenderSummary(
            senderName = senderName,
            messageCount = senderMessages.size,
            latestMessageMillis = senderMessages.maxOf { it.sentAtMillis })
    }.sortedWith(
        compareByDescending<SenderSummary> { it.messageCount }.thenBy { it.senderName }
    )
}