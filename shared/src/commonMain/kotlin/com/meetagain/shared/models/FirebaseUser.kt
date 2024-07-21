package com.meetagain.shared.models

data class FirebaseUser(
    val uid: String,
    val displayName: String?,
    val email: String?,
    val phoneNumber: String?
)
