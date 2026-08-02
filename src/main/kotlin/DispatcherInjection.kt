import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import java.io.IOException
import kotlin.time.Duration.Companion.milliseconds

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
    ): LoadResult<String> = try {
        val message = loadMessage(messageId = messageId)
        LoadResult.Success(message)
    } catch (e: IOException) {
        LoadResult.Failure(e.message ?: "Unknown I/O error")
    }

    suspend fun fetchMessageWithTimeout(
        messageId: MessageId,
        fetchDelayMillis: Long,
        timeoutMillis: Long,
    ): String? =
        withTimeoutOrNull(timeoutMillis.milliseconds) {
            fetchMessage(messageId = messageId, delayMillis = fetchDelayMillis)
        }
}