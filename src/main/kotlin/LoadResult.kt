sealed interface LoadResult<out T> {
    data class Success<T>(
        val value: T,
    ) : LoadResult<T>

    data class Failure(
        val reason: String,
    ) : LoadResult<Nothing>
}

fun <T, R> LoadResult<T>.map(
    transform: (T) -> R,
): LoadResult<R> = when (this) {
    is LoadResult.Success<T> -> LoadResult.Success(transform(value))
    is LoadResult.Failure -> this
}

fun <T> LoadResult<T>.getOrElse(
    fallback: (reason: String) -> T,
): T = when (this) {
    is LoadResult.Success -> value
    is LoadResult.Failure -> fallback(reason)
}

fun <T : Comparable<T>> larger(
    first: T,
    second: T,
): T = if (first >= second) first else second

inline fun <reified T> Any?.castOrNull(): T? = this as? T