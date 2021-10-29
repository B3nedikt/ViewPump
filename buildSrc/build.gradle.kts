plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.3")
    implementation(kotlin("gradle-plugin", "1.5.31"))
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.5.30")
}