data class MessageDto(
    val id: String?,
    val text: String?,
    val senderName: String?,
    val sentAtMillis: Long?,
)

data class MessagePreview(
    val id: String,
    val text: String,
    val senderName: String,
    val sentAtMillis: Long,
)

fun buildMessagePreviews(
    messages: List<MessageDto>?
): List<MessagePreview> {
    return messages.orEmpty().mapNotNull { dto ->
        val id = dto.id?.trim()?.takeIf { it.isNotBlank() } ?: return@mapNotNull null
        val text = dto.text?.trim()?.takeIf { it.isNotBlank() } ?: return@mapNotNull null
        val senderName = dto.senderName?.trim()?.takeIf { it.isNotBlank() } ?: "Unknown"
        val sentAtMillis = dto.sentAtMillis ?: return@mapNotNull null

        MessagePreview(id = id, text = text, senderName = senderName, sentAtMillis = sentAtMillis)
    }.sortedByDescending { it.sentAtMillis }
}