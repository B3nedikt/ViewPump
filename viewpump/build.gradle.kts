import com.vanniktech.maven.publish.AndroidSingleVariantLibrary
import com.vanniktech.maven.publish.JavadocJar
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.library")
    id("kotlin-android")
    alias(libs.plugins.dokka)
    id("com.vanniktech.maven.publish") version "0.36.0"
}

configure<com.android.build.api.dsl.LibraryExtension> {
    compileSdk = 33

    defaultConfig {
        minSdk = 16
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = false
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
    namespace = "dev.b3nedikt.viewpump"
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(platform(libs.kotlin.bom))

    implementation(libs.kotlin.stdlib)
    implementation(libs.appCompat)
}

mavenPublishing {

    publishToMavenCentral()
    signAllPublications()

    configure(
        AndroidSingleVariantLibrary(
            variant = "release",
            javadocJar = JavadocJar.Dokka("dokkaGenerateHtml")
        )
    )

    coordinates("dev.b3nedikt.viewpump", "viewpump", project.version.toString())

    pom {
        name.set("ViewPump")
        description.set("View inflation you can intercept.")
        url.set("https://github.com/B3nedikt/viewpump")

        licenses {
            license {
                name.set("The Apache Software License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("b3nedikt")
                name.set("Benedikt Löbbecke")
                email.set("mail@b3nedikt.dev")
                organization.set("b3nedikt.dev")
                organizationUrl.set("https://github.com/B3nedikt/viewpump")
            }
        }
        scm {
            url.set("https://github.com/B3nedikt/viewpump")
        }
    }
}
