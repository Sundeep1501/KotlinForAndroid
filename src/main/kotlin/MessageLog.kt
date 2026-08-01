data class MessageLog(
    val messageId: String,
    val conversationId: String,
    val statusText: String,
)

fun createMessageLog(
    message: ChatMessage?,
): MessageLog? = message?.run {
    MessageLog(
        messageId = id.value,
        conversationId = conversationId.value,
        statusText = deliveryStatus.toDisplayText()
    )
}