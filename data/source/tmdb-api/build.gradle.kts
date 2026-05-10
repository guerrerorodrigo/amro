plugins {
    alias(libs.plugins.amro.api.library)
    alias(libs.plugins.kotlin.serialization.plugin)
}

android {
    namespace = "com.rodrigoguerrero.data.source.tmdb.api"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
