plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")

}

android {
    namespace = "com.stevenlopez.block2.p1.wundersale"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.stevenlopez.block2.p1.wundersale"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    plugins {
        kotlin("kapt") version "1.6.0"
    }

    dependencies {
        // Loading button
        implementation("br.com.simplepass:loading-button-android:2.3.0")

        // Glide
        implementation("com.github.bumptech.glide:glide:4.13.0")

        // Circular image
        implementation("de.hdodenhof:circleimageview:3.1.0")

        // ViewPager2 indicator
        implementation("io.github.vejei.viewpagerindicator:viewpagerindicator:1.0.0-alpha.2")

        // StepView
        implementation("com.shuhart.stepview:stepview:1.5.1")

        // Android KTX
        implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")

        // Dagger Hilt
        implementation("com.google.dagger:hilt-android:2.41.1")
        kapt("com.google.dagger:hilt-compiler:2.41.1")
    }



}