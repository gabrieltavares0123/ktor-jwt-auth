package gabriel.github.io.plugins

import gabriel.github.io.authenticate
import gabriel.github.io.data.user.UserDataSource
import gabriel.github.io.getSecretInfo
import gabriel.github.io.security.hashing.HashingService
import gabriel.github.io.security.token.TokenConfig
import gabriel.github.io.security.token.TokenService
import gabriel.github.io.signIn
import gabriel.github.io.signUp
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting(
    userDataSource: UserDataSource,
    hashingService: HashingService,
    tokenService: TokenService,
    tokenConfig: TokenConfig,
) {
    routing {
        signIn(userDataSource, hashingService, tokenService, tokenConfig)
        signUp(hashingService, userDataSource)
        authenticate()
        getSecretInfo()
    }
}
