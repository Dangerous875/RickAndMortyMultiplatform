package com.klyxdevs.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun getCurrentTarget(): Target

enum class Target {
    IOS, Android, Desktop
}

fun isDesktop(): Boolean = getCurrentTarget() == Target.Desktop