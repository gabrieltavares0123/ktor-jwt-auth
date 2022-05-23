package gabriel.github.io.security.hashing

interface HashingService {
    fun generate(value: String, saltLength: Int = 32): SaltedHash
    fun verify(value: String, saltedHash: SaltedHash): Boolean
}