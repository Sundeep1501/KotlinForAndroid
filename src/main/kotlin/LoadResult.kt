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