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

data class BatteryUpdate(
    val vehicleId: String,
    val batteryPercentage: Int,
    val timestamp: Long
)

fun latestBatteryLevels(
    updates: List<BatteryUpdate>
): Map<String, Int> {
    val map = mutableMapOf<String, BatteryUpdate>()
    updates.forEach {
        if (it.batteryPercentage in 0..100) {
            val current = map.getOrPut(it.vehicleId) { it }
            if (current.timestamp < it.timestamp) {
                map[it.vehicleId] = it
            }
        }
    }
    return map.values.associate {
        it.vehicleId to it.batteryPercentage
    }
}

fun main() {
    data class Transaction(
        val id: String,
        val userId: String,
        val amount: Int
    )


}