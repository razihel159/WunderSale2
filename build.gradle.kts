
buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        // Dagger Hilt Android Gradle plugin
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.41.1")

        // Google Services plugin
        classpath("com.google.gms:google-services:4.4.1")

        // Navigation Safe Args Gradle plugin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}



plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}