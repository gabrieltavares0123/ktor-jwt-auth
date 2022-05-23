package gabriel.github.io.security.token

interface TokenService {
    fun generate(
        config: TokenConfig,
        vararg claims: TokenClaim,
    ): String
}