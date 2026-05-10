plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.hilt)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.domain.home.impl"
}

dependencies {
    implementation(project(":domain:common"))
    implementation(project(":domain:home:home-api"))
    implementation(project(":data:repository:movies:movies-api"))
}
