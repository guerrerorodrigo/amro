plugins {
    alias(libs.plugins.amro.android.library)
    alias(libs.plugins.amro.hilt)
    alias(libs.plugins.amro.unit.test)
}

android {
    namespace = "com.rodrigoguerrero.domain.details.impl"
}

dependencies {
    implementation(project(":domain:common"))
    implementation(project(":domain:details:details-api"))
    implementation(project(":data:repository:movies:movies-api"))
}
