data class SyncRequest(
    val conversationId: ConversationId,
    val retryCount: Int,
    val requiresNetwork: Boolean,
)

class SyncRequestBuilder {
    lateinit var conversationId: ConversationId
    var retryCount: Int = 0
    var requiresNetwork: Boolean = false

    fun build(): SyncRequest =
        SyncRequest(
            conversationId = conversationId,
            retryCount = retryCount,
            requiresNetwork = requiresNetwork,
        )
}

fun createSyncRequest(
    conversationId: ConversationId,
): SyncRequest = SyncRequestBuilder().apply {
    this.conversationId = conversationId
    this.retryCount = 3
    this.requiresNetwork = true
}.build()