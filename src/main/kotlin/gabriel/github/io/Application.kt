package gabriel.github.io

import gabriel.github.io.data.user.MongoUserDataSource
import gabriel.github.io.data.user.User
import io.ktor.server.application.*
import gabriel.github.io.plugins.*
import gabriel.github.io.security.hashing.SHA256HashingService
import gabriel.github.io.security.token.JwtTokenService
import gabriel.github.io.security.token.TokenConfig
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val mongoPassowrd = System.getenv("MONGO_PASSWORD")
    val dbName = "ktor-auth-db"
    val mongoDb = KMongo.createClient(
        connectionString = "mongodb+srv://gabrieltavares:$mongoPassowrd@cluster0.tbqh0.mongodb.net/$dbName?retryWrites=true&w=majority"
    ).coroutine.getDatabase(dbName)

    val userDataSource = MongoUserDataSource(mongoDb)
    val tokenService = JwtTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").getString(),
        audience = environment.config.property("jwt.audience").getString(),
        expiresIn = 1000L * 60L * 60L * 24L * 365L,
        secret = System.getenv("JWT_SECRET")
    )
    val hashingService = SHA256HashingService()

    configureRouting(
        userDataSource = userDataSource,
        hashingService = hashingService,
        tokenService = tokenService,
        tokenConfig = tokenConfig
    )
    configureSerialization()
    configureMonitoring()
    configureSecurity(tokenConfig)
}
