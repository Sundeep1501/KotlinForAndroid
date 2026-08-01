fun firstThreeFailedMessageIds(
    messages: List<ChatMessage>,
): List<MessageId> =
    messages
        .asSequence()
        .filter { it.deliveryStatus is DeliveryStatus.Failed }
        .map { it.id }
        .take(3)
        .toList()

fun <T, K> indexBy(
    items: List<T>,
    keySelector: (T) -> K,
): Map<K, T> = items.associateBy(keySelector).also { map ->
    require(items.size == map.size) { "Duplicate keys" }
}
