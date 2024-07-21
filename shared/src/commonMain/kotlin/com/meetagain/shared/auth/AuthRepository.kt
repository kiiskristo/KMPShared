package com.meetagain.shared.auth

import com.meetagain.shared.models.FirebaseUser
import dev.gitlive.firebase.auth.PhoneVerificationProvider
import kotlinx.coroutines.flow.Flow
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

interface AuthRepository {
    suspend fun sendVerificationCode(phoneNumber: String, verificationProvider: PhoneVerificationProvider)
    suspend fun login(): AuthResult
    suspend fun logout()
    fun getCurrentUser(): FirebaseUser?
    @NativeCoroutines
    val authStateChanged: Flow<FirebaseUser?>
}