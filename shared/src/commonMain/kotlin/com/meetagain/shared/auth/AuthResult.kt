package com.meetagain.shared.auth

import dev.gitlive.firebase.auth.AuthResult as FirebaseAuthResult
import com.meetagain.shared.models.FirebaseUser

data class AuthResult(val user: FirebaseUser?)

fun FirebaseAuthResult.toSharedAuthResult(): AuthResult {
    return AuthResult(
        user = this.user?.let { FirebaseUser(it.uid, it.displayName, it.email, it.phoneNumber) }
    )
}
