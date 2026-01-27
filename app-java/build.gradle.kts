plugins {
    id("com.android.application")
}

configure<com.android.build.api.dsl.ApplicationExtension> {
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
