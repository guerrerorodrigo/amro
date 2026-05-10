plugins {
    alias(libs.plugins.amro.feature.compose)
}

android {
    namespace = "com.rodrigoguerrero.ui.feature.details"
}

dependencies {
    implementation(project(":domain:details:details-api"))
}
