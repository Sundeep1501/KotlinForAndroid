data class MessageLog(
    val messageId: String,
    val conversationId: String,
    val statusText: String,
)

fun createMessageLog(
    message: ChatMessage?,
): MessageLog? = message?.let {
    MessageLog(
        messageId = it.id.value,
        conversationId = it.conversationId.value,
        statusText = it.deliveryStatus.toDisplayText()
    )
}