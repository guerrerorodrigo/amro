plugins {
    alias(libs.plugins.amro.feature.compose)
}

android {
    namespace = "com.rodrigoguerrero.ui.home"
}

dependencies {
    implementation(project(":domain:home:home-api"))
}
