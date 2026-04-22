plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.serialization.plugin)
}

android {
    namespace = "com.rodrigoguerrero.data.source.tmdb.api"
    compileSdk = 36
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}
