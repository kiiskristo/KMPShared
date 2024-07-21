package com.meetagain.shared.auth

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.PhoneAuthProvider
import dev.gitlive.firebase.auth.PhoneVerificationProvider
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.meetagain.shared.models.FirebaseUser
import dev.gitlive.firebase.auth.AuthCredential
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines

class IOSAuthRepository : AuthRepository {
    private val auth: FirebaseAuth = Firebase.auth
    private var credential: AuthCredential? = null

    override suspend fun sendVerificationCode(phoneNumber: String, verificationProvider: PhoneVerificationProvider) {
        try {
            println("Sending verification code to $phoneNumber")
            val authCredential = PhoneAuthProvider(auth).verifyPhoneNumber(phoneNumber, verificationProvider)
            credential = authCredential
            println("Verification code sent successfully, credential: $authCredential")
        } catch (e: Exception) {
            println("Failed to send verification code: ${e.message}")
            throw e
        }
    }

    override suspend fun login(): AuthResult {
        try {
            println("Login successful with credential $credential")
            if (credential == null) {
                throw IllegalStateException("Login attempt without verification or verification expired.")
            }
            val result = auth.signInWithCredential(credential!!)
            println("Login successful")
            return result.toSharedAuthResult()
        } catch (e: Exception) {
            println("Failed to login with verification code: ${e.message}")
            throw e
        }
    }

    override suspend fun logout() {
        try {
            println("Logging out")
            auth.signOut()
            println("Logged out successfully")
        } catch (e: Exception) {
            println("Failed to logout: ${e.message}")
            throw e
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        val user = auth.currentUser
        println("Current user: $user")
        return user?.let { FirebaseUser(it.uid, it.displayName, it.email, it.phoneNumber) }
    }

    @NativeCoroutines
    override val authStateChanged: Flow<FirebaseUser?> = auth.authStateChanged.map {
        it?.let { user -> FirebaseUser(user.uid, user.displayName, user.email, user.phoneNumber) }
    }
}
