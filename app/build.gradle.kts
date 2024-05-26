plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = "dev.b3nedikt.viewpump.example"
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
    namespace = "dev.b3nedikt.viewpump.sample"
}

kotlin {
    jvmToolchain(17)
}

dependencies {

    implementation(project(":viewpump"))

    implementation(libs.kotlin.stdlib)

    implementation(libs.appCompat)

    implementation(libs.appLocale)

    // Testing
    testImplementation(libs.junit)
    //testImplementation(libs.junit.ktx)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.monitor)
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.espresso.web)
}
