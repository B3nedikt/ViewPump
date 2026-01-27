plugins {
    alias(libs.plugins.android.application)
}

configure<com.android.build.api.dsl.ApplicationExtension> {
    compileSdk = 33

    defaultConfig {
        applicationId = "dev.b3nedikt.viewpump.example"
        minSdk = 16
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    namespace = "dev.b3nedikt.viewpump.sample"
}

dependencies {

    implementation(project(":viewpump"))

    implementation(libs.appCompat)
    implementation(libs.appLocale)
}
