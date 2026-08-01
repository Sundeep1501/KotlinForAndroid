data class MessageStatistics(
    val total: Int = 0,
    val sending: Int = 0,
    val sent: Int = 0,
    val delivered: Int = 0,
    val failed: Int = 0,
)

/*
// Traverse the list 4 times, one for each count.
fun calculateStatistics(
    messages: List<ChatMessage>,
): MessageStatistics {
    return MessageStatistics(
        total = messages.size,
        sending = messages.count { it.deliveryStatus is DeliveryStatus.Sending },
        sent = messages.count { it.deliveryStatus is DeliveryStatus.Sent },
        delivered = messages.count { it.deliveryStatus is DeliveryStatus.Delivered },
        failed = messages.count { it.deliveryStatus is DeliveryStatus.Failed })
}*/
/*
// creates N number of MessageStatistics objects
fun calculateStatistics(
    messages: List<ChatMessage>,
): MessageStatistics =
    messages.fold(MessageStatistics()) { statistic, message ->
        when (message.deliveryStatus) {
            is DeliveryStatus.Failed ->
                statistic.copy(total = statistic.total + 1, failed = statistic.failed + 1)

            is DeliveryStatus.Sent ->
                statistic.copy(total = statistic.total + 1, sent = statistic.sent + 1)

            DeliveryStatus.Sending ->
                statistic.copy(total = statistic.total + 1, sending = statistic.sending + 1)

            is DeliveryStatus.Delivered ->
                statistic.copy(total = statistic.total + 1, delivered = statistic.delivered + 1)
        }
    }
*/

// Traverse the list once, with one object
fun calculateStatistics(
    messages: List<ChatMessage>,
): MessageStatistics {
    var failed = 0
    var sent = 0
    var sending = 0
    var delivered = 0
    for (message in messages) {
        when (message.deliveryStatus) {
            is DeliveryStatus.Delivered -> delivered++
            is DeliveryStatus.Failed -> failed++
            DeliveryStatus.Sending -> sending++
            is DeliveryStatus.Sent -> sent++
        }
    }
    return MessageStatistics(
        total = messages.size,
        sending = sending,
        sent = sent,
        delivered = delivered,
        failed = failed
    )
}
