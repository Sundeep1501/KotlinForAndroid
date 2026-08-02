import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.IOException

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

    suspend fun loadMessageResult(
        messageId: MessageId,
    ): LoadResult<String> =
        try {
            val message = loadMessage(messageId = messageId)
            LoadResult.Success(message)
        } catch (e: IOException) {
            LoadResult.Failure(e.message ?: "Unknown I/O error")
        }
}