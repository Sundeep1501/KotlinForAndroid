data class ChatMessage(
    val id: MessageId,
    val conversationId: ConversationId,
    val deliveryStatus: DeliveryStatus,
)

sealed interface DeliveryStatus {
    data object Sending : DeliveryStatus
    data class Sent(val serverTimestampMillis: Long) : DeliveryStatus
    data class Delivered(val deliveredAtMillis: Long) : DeliveryStatus
    data class Failed(val reason: String) : DeliveryStatus
}

fun DeliveryStatus.toDisplayText(): String = when (this) {
    DeliveryStatus.Sending ->
        "Sending..."

    is DeliveryStatus.Sent ->
        "Sent at $serverTimestampMillis"

    is DeliveryStatus.Delivered ->
        "Delivered at $deliveredAtMillis"

    is DeliveryStatus.Failed ->
        "Failed: $reason"

}

fun updateDeliveryStatus(
    messages: List<ChatMessage>,
    messageId: MessageId,
    newStatus: DeliveryStatus,
): List<ChatMessage> = messages.map {
    if (it.id == messageId) {
        it.copy(deliveryStatus = newStatus)
    } else it
}

fun findMessageStatusText(
    messages: List<ChatMessage>,
    messageId: MessageId,
): String = messages.firstOrNull { it.id == messageId }?.deliveryStatus?.toDisplayText() ?: "Message not found"

@JvmInline
value class MessageId(val value: String) {
    init {
        require(value.isNotBlank()) { "Message ID cannot be blank" }
    }
}

@JvmInline
value class ConversationId(val value: String) {
    init {
        require(value.isNotBlank()) { "Conversation ID cannot be blank" }
    }
}

fun belongsToConversation(
    message: ChatMessage,
    conversationId: ConversationId,
): Boolean = message.conversationId == conversationId
