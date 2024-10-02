package com.klyxdevs.rickmortyapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform