package com.yeetsies.core.repository.login

import com.yeetsies.core.rest.Result
import com.yeetsies.core.ui.models.User

class LoginRepositoryImpl : LoginRepository {
    override fun login(username: String, password: String): Result<User> {
        return Result.Success(User(username, ""))
    }
}
