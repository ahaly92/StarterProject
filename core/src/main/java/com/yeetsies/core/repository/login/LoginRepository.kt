package com.yeetsies.core.repository.login

import com.yeetsies.core.rest.Result
import com.yeetsies.core.ui.models.User

interface LoginRepository {
    fun login(username: String, password: String): Result<User>
}
