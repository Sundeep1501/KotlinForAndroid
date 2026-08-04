import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

suspend fun countCompletedMessages(): Int =
    coroutineScope {
        var count = 0
        val mutex = Mutex()

        val jobs = List(1_000) {
            launch(Dispatchers.Default) {
                // Safely increment
                mutex.withLock { count++ }
            }
        }
        jobs.joinAll()

        count
    }

suspend fun countCompletedMessagesAggregation(): Int = coroutineScope {
    List(1_000) {
        100
    }.sum()
}