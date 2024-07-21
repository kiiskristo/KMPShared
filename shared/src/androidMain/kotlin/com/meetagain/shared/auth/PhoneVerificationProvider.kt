package com.meetagain.shared.auth

import android.app.Activity
import java.util.concurrent.TimeUnit

interface PhoneVerificationProvider {
    val activity: Activity
    val timeout: Long
    val unit: TimeUnit
    fun codeSent(triggerResend: (Unit) -> Unit)
    suspend fun getVerificationCode(): String
}