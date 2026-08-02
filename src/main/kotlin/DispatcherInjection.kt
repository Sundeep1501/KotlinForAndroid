import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

fun interface BlockingMessageSource {
    fun loadMessage(messageId: MessageId): String
}

class MessageRepository(
    private val source: BlockingMessageSource,
    private val ioDispatcher: CoroutineDispatcher,
) {
    suspend fun loadMessage(
        messageId: MessageId,
    ): String = withContext(ioDispatcher) {
        source.loadMessage(messageId)
    }
}