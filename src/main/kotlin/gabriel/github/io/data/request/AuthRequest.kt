package gabriel.github.io.data.request

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val name: String,
    val password: String,
)
