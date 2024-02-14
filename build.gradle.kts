
buildscript {
    repositories {
        google()
    }
    dependencies {
        // Dagger Hilt Android Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.50")

        // Google Services plugin
        classpath("com.google.gms:google-services:4.4.1")

        // Navigation Safe Args Gradle plugin
        def nav_version = "2.5.0"
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}



plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}