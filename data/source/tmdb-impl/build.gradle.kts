import java.util.Properties

plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.hilt)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.data.source.tmdb.impl"
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
}

dependencies {
    implementation(project(":data:source:tmdb-api"))
    implementation(libs.bundles.ktor)

    testImplementation(libs.io.ktor.ktor.client.mock)
}
