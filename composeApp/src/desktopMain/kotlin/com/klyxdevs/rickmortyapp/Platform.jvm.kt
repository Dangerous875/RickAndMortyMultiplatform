package com.klyxdevs.rickmortyapp

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual fun getCurrentTarget(): Target {
    return Target.Desktop
}