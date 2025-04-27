package entities

data class Space(
    val id: Int,
    val title: String,
    val description: String,
    val ownerId: String,
    val isPrivate: Boolean,
    val participants: List<User>,
    val createAt: String
)