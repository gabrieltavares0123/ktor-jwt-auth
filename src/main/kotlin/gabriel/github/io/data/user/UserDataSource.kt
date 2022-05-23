package gabriel.github.io.data.user

interface UserDataSource {
    suspend fun getUserByName(name: String): User?
    suspend fun insertUser(user: User): Boolean
}