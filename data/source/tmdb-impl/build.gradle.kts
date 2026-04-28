import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.amro.hilt)
}

android {
    namespace = "com.rodrigoguerrero.data.source.tmdb.impl"
    compileSdk {
        version = release(36)
    }

    val localProperties = Properties().apply {
        val file = rootProject.file("local.properties")
        if (file.exists()) {
            load(file.inputStream())
        }
    }

    val myApiKey: String = localProperties.getProperty("TMDB_API_KEY") ?: ""

    defaultConfig {
        buildConfigField("String", "TMDB_API_KEY", "\"$myApiKey\"")
    }

    buildFeatures {
        buildConfig = true
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform {
                includeEngines("junit-jupiter")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":data:source:tmdb-api"))
    implementation(libs.bundles.ktor)

    testImplementation(libs.io.ktor.ktor.client.mock)
    testImplementation(libs.bundles.junit)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
