package gabriel.github.io.response

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponse(
    val token: String,
)
