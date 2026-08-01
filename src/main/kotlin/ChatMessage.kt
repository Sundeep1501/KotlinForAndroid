data class ChatMessage(
    val id: String,
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
    messageId: String,
    newStatus: DeliveryStatus,
): List<ChatMessage> = messages.map {
    if (it.id == messageId) {
        it.copy(deliveryStatus = newStatus)
    } else it
}
