fun normalizeParticipantNames(
    names: List<String?>?,
): List<String> {
    return names.orEmpty()
        .mapNotNull { name ->
            name?.trim()?.takeIf { it.isNotBlank() }?.lowercase()
        }
        .distinct()
        .sorted()
}