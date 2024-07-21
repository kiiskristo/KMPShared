package com.meetagain.shared.models

import kotlinx.serialization.Serializable
import kotlinx.datetime.LocalDateTime

@Serializable
data class Message(
    val id: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val timestamp: LocalDateTime
)