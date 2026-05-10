plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.rodrigoguerrero.theme"
}

dependencies {
    implementation(project(":domain:home:home-api"))
    implementation(project(":ui:common:mvi"))

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    implementation(libs.kotlinx.collections.immutable)
}
