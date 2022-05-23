package gabriel.github.io.data.user

import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.insertOne
import org.litote.kmongo.eq

class MongoUserDataSource(
    private val mongoDb: CoroutineDatabase,
): UserDataSource {
    private val users = mongoDb.getCollection<User>()

    override suspend fun getUserByName(name: String): User? {
        return users.findOne(User::name eq name)
    }

    override suspend fun insertUser(user: User): Boolean {
        return users.insertOne(user).wasAcknowledged()
    }
}