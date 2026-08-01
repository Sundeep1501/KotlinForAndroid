fun firstThreeFailedMessageIds(
    messages: List<ChatMessage>,
): List<MessageId> =
    messages
        .asSequence()
        .filter { it.deliveryStatus is DeliveryStatus.Failed }
        .map { it.id }
        .take(3)
        .toList()


fun indexMessagesById(
    messages: List<ChatMessage>,
): Map<MessageId, ChatMessage> {
    val messagesById = messages.associateBy { message -> message.id }
    require(messagesById.size == messages.size) { "Duplicate message IDs" }
    return messagesById
}